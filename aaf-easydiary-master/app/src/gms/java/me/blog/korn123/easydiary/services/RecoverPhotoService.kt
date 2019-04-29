package me.blog.korn123.easydiary.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.drive.*
import com.google.android.gms.drive.events.OpenFileCallback
import com.google.android.gms.drive.query.Filters
import com.google.android.gms.drive.query.Query
import com.google.android.gms.drive.query.SearchableField
import me.blog.korn123.easydiary.R
import me.blog.korn123.easydiary.activities.DiaryMainActivity
import me.blog.korn123.easydiary.helper.*
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException

class RecoverPhotoService(name: String = "RecoverPhotoService") : IntentService(name) {
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManager
    private lateinit var mMetadataBuffer: MetadataBuffer
    private var driveResourceClient: DriveResourceClient? = null
    private var remoteDriveFileCount = 0
    private var duplicateFileCount = 0
    private var successCount = 0
    private var failCount = 0
    private var mInProcessJob = true
    private var targetIndexesCursor = 0
    private val targetIndexes = arrayListOf<Int>()
    private val photoPath = "${Environment.getExternalStorageDirectory().absolutePath}$AAF_EASY_DIARY_PHOTO_DIRECTORY"
    
    override fun onCreate() {
//        Handler().post { Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show() }
        super.onCreate()
        GoogleSignIn.getLastSignedInAccount(this)?.let {
            driveResourceClient = Drive.getDriveResourceClient(this, it)
        }
        notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
            // Create the NotificationChannel
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance)
            mChannel.description = NOTIFICATION_CHANNEL_DESCRIPTION
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mInProcessJob = false
//        Handler().post { Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show() }
    }
    
    override fun onHandleIntent(intent: Intent?) {
//        Handler().post { Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show() }
        mInProcessJob = true
        notificationManager.cancel(NOTIFICATION_COMPLETE_ID)
        notificationBuilder
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.cloud_download)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_round))
                .setOnlyAlertOnce(true)
                .setContentTitle(getString(R.string.recover_attach_photo_title))
                .addAction(
                        R.drawable.cloud_download,
                        getString(R.string.cancel),
                        PendingIntent.getService(this, 0, Intent(this, NotificationService::class.java).apply {
                            action = NotificationService.ACTION_RECOVER_CANCEL
                        }, 0)
                )
        startForeground(NOTIFICATION_FOREGROUND_ID, notificationBuilder.build())

        intent?.let {
            recoverPhoto(DriveId.decodeFromString(it.getStringExtra(NOTIFICATION_DRIVE_ID)).asDriveFolder())
        }

        // FIXME Hold async job???
        while(mInProcessJob) {
            Thread.sleep(1000)
        }
    }

    private fun recoverPhoto(folder: DriveFolder) {
        val query = Query.Builder()
                .addFilter(
                        Filters.and(
                                Filters.eq(SearchableField.MIME_TYPE, AAF_EASY_DIARY_PHOTO),
                                Filters.eq(SearchableField.TRASHED, false)
                        )
                )
                .build()
        val queryTask = driveResourceClient?.queryChildren(folder, query)
        queryTask?.addOnSuccessListener { it ->
            mMetadataBuffer = it
            mMetadataBuffer.forEachIndexed { index, metadata ->
                if (!File("$photoPath${metadata.title}").exists()) targetIndexes.add(index)
            }

            remoteDriveFileCount = mMetadataBuffer.count
            duplicateFileCount = remoteDriveFileCount - targetIndexes.size
            when (targetIndexes.size) {
                0 -> updateNotification()
                else -> {
                    val metaData = mMetadataBuffer[targetIndexes[targetIndexesCursor++]]
                    retrieveContents(metaData.driveId.asDriveFile(), "$photoPath${metaData.title}")
                } 
            }
        }
    }

    private fun retrieveContents(file: DriveFile, destFilePath: String) {
        val openCallback = object : OpenFileCallback() {
            override fun onProgress(bytesDownloaded: Long, bytesExpected: Long) {}
            override fun onContents(driveContents: DriveContents) {
                // [START_EXCLUDE]
                try {
                    FileUtils.copyInputStreamToFile(driveContents.inputStream, File(destFilePath))
                    successCount++
                    updateNotification()
                } catch (e: IOException) {
                    failCount++
                    updateNotification()
                }
                // [END_EXCLUDE]
            }
            override fun onError(e: Exception) {
                failCount++
                updateNotification()
            }
        }

        driveResourceClient?.openFile(file, DriveFile.MODE_READ_ONLY, openCallback)
    }

    private fun updateNotification() {
        if (targetIndexes.size == 0) {
            launchCompleteNotification(getString(R.string.notification_msg_download_invalid))
        } else {
            notificationBuilder
                    .setStyle(NotificationCompat.InboxStyle()
                        .addLine("${getString(R.string.notification_msg_google_drive_file_count)}: $remoteDriveFileCount")
                        .addLine("${getString(R.string.notification_msg_duplicate_file_count)}: $duplicateFileCount")
                        .addLine("${getString(R.string.notification_msg_download_success)}: $successCount")
                        .addLine("${getString(R.string.notification_msg_download_fail)}: $failCount")
                    )
                    .setContentTitle("${getString(R.string.notification_msg_download_progress)}  ${successCount + failCount}/${targetIndexes.size}")
                    .setProgress(targetIndexes.size, successCount + failCount, false)
            notificationManager.notify(NOTIFICATION_FOREGROUND_ID, notificationBuilder.build())

            if (successCount + failCount < targetIndexes.size) {
                when (mInProcessJob) {
                    true -> {
                        val metaData = mMetadataBuffer[targetIndexes[targetIndexesCursor++]]
                        retrieveContents(metaData.driveId.asDriveFile(), "$photoPath${metaData.title}")
                    }
                    false -> notificationManager.cancel(NOTIFICATION_FOREGROUND_ID)
                }
            } else {
                launchCompleteNotification(getString(R.string.notification_msg_download_complete))
            }
        }
//        if (currentCount == targetIndexes.size) finish()
    }
    
    private fun launchCompleteNotification(contentText: String) {
        val resultNotificationBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
        resultNotificationBuilder
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(NotificationCompat.InboxStyle()
                        .addLine("${getString(R.string.notification_msg_google_drive_file_count)}: $remoteDriveFileCount")
                        .addLine("${getString(R.string.notification_msg_duplicate_file_count)}: $duplicateFileCount")
                        .addLine("${getString(R.string.notification_msg_download_success)}: $successCount")
                        .addLine("${getString(R.string.notification_msg_download_fail)}: $failCount")
                )
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.cloud_download)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_round))
                .setOnlyAlertOnce(true)
                .setOngoing(false)
                .setAutoCancel(true)
                .setContentTitle(getString(R.string.recover_attach_photo_title))
                .setContentText(contentText)
                .setContentIntent(
                        PendingIntent.getActivity(this, 0, Intent(this, DiaryMainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }, PendingIntent.FLAG_UPDATE_CURRENT)
                )
                .addAction(
                        R.drawable.cloud_download,
                        getString(R.string.dismiss),
                        PendingIntent.getService(this, 0, Intent(this, NotificationService::class.java).apply {
                            action = NotificationService.ACTION_DISMISS
                        }, 0)
                )
        notificationManager.notify(NOTIFICATION_COMPLETE_ID, resultNotificationBuilder.build())
        mInProcessJob = false
        remoteDriveFileCount = 0
        duplicateFileCount = 0
        successCount = 0
        failCount = 0
        targetIndexes.clear()
        stopSelf()
    }
}