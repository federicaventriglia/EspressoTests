package me.blog.korn123.easydiary.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.xw.repo.BubbleSeekBar
import io.github.aafactory.commons.activities.BaseWebViewActivity
import io.github.aafactory.commons.helpers.BaseConfig
import kotlinx.android.synthetic.main.activity_settings_foss.*
import me.blog.korn123.commons.utils.EasyDiaryUtils
import me.blog.korn123.commons.utils.FontUtils
import me.blog.korn123.easydiary.BuildConfig
import me.blog.korn123.easydiary.R
import me.blog.korn123.easydiary.adapters.FontItemAdapter
import me.blog.korn123.easydiary.adapters.ThumbnailSizeItemAdapter
import me.blog.korn123.easydiary.extensions.*
import me.blog.korn123.easydiary.helper.EXTERNAL_STORAGE_PERMISSIONS
import me.blog.korn123.easydiary.helper.REQUEST_CODE_EXTERNAL_STORAGE_WITH_FONT_SETTING
import me.blog.korn123.easydiary.helper.TransitionHelper
import me.blog.korn123.easydiary.helper.USER_CUSTOM_FONTS_DIRECTORY
import org.apache.commons.io.FilenameUtils
import java.io.File
import java.util.*

/**
 * Created by CHO HANJOONG on 2017-11-04.
 */

class SettingsActivity : EasyDiaryActivity() {
    private var mAlertDialog: AlertDialog? = null

    private val mOnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.primaryColor -> TransitionHelper.startActivityWithTransition(this@SettingsActivity, Intent(this@SettingsActivity, CustomizationActivity::class.java))
            R.id.fontSetting -> if (checkPermission(EXTERNAL_STORAGE_PERMISSIONS)) {
                openFontSettingDialog()
            } else {
                confirmPermission(EXTERNAL_STORAGE_PERMISSIONS, REQUEST_CODE_EXTERNAL_STORAGE_WITH_FONT_SETTING)
            }
            R.id.thumbnailSetting -> {
                openThumbnailSettingDialog()
            }
            R.id.sensitiveOption -> {
                sensitiveOptionSwitcher.toggle()
                config.diarySearchQueryCaseSensitive = sensitiveOptionSwitcher.isChecked
            }
            R.id.addTtfFontSetting -> {
                openGuideView()
            }
            R.id.restorePhotoSetting -> {
                openGuideView()
            }
            R.id.rateAppSetting -> openGooglePlayBy("me.blog.korn123.easydiary")
            R.id.licenseView -> {
                val licenseIntent = Intent(this, WebViewActivity::class.java)
                licenseIntent.putExtra(BaseWebViewActivity.OPEN_URL_INFO, "https://github.com/hanjoongcho/aaf-easydiary/blob/master/LICENSE.md")
                startActivity(licenseIntent)
            }
            R.id.releaseNotes -> checkWhatsNewDialog(false)
            R.id.boldStyleOption -> {
                boldStyleOptionSwitcher.toggle()
                config.boldStyleEnable = boldStyleOptionSwitcher.isChecked
            }
            R.id.multiPickerOption -> {
                multiPickerOptionSwitcher.toggle()
                config.multiPickerEnable = multiPickerOptionSwitcher.isChecked
            }
            R.id.appLockSetting -> {
                when (config.aafPinLockEnable) {
                    true -> {
                        if (config.fingerprintLockEnable) {
                            showAlertDialog(getString(R.string.pin_release_need_fingerprint_disable), null)
                        } else {
                            appLockSettingSwitcher.isChecked = false
                            config.aafPinLockEnable = false
                            showAlertDialog(getString(R.string.pin_setting_release), null)    
                        }
                    }
                    false -> {
                        startActivity(Intent(this, PinLockActivity::class.java).apply {
                            putExtra(FingerprintLockActivity.LAUNCHING_MODE, PinLockActivity.ACTIVITY_SETTING)
                        })
                    }
                }
            }
            R.id.fingerprint -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    when (config.fingerprintLockEnable) {
                        true -> {
                            fingerprintSwitcher.isChecked = false
                            config.fingerprintLockEnable = false
                            showAlertDialog(getString(R.string.fingerprint_setting_release), null)
                        }
                        false -> {
                            when (config.aafPinLockEnable) {
                                true -> {
                                    startActivity(Intent(this, FingerprintLockActivity::class.java).apply {
                                        putExtra(FingerprintLockActivity.LAUNCHING_MODE, FingerprintLockActivity.ACTIVITY_SETTING)
                                    })        
                                }
                                false -> {
                                    showAlertDialog(getString(R.string.fingerprint_lock_need_pin_setting), null)
                                }
                            }
                        }
                    }    
                } else {
                    showAlertDialog(getString(R.string.fingerprint_not_available), null)
                }
            }
            R.id.enableCardViewPolicy -> {
                enableCardViewPolicySwitcher.toggle()
                config.enableCardViewPolicy = enableCardViewPolicySwitcher.isChecked
                updateCardViewPolicy(main_holder)
            }
            R.id.decreaseFont -> {
                config.settingFontSize = config.settingFontSize - 5
                initTextSize(main_holder, this)
            }
            R.id.increaseFont -> {
                config.settingFontSize = config.settingFontSize + 5
                initTextSize(main_holder, this)
            }
            R.id.contentsSummary -> {
                contentsSummarySwitcher.toggle()
                config.enableContentsSummary = contentsSummarySwitcher.isChecked 
            }
        }
    }
    
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_foss)
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setTitle(R.string.settings)
            setDisplayHomeAsUpEnabled(true)
        }

        bindEvent()
        EasyDiaryUtils.changeDrawableIconColor(this, config.primaryColor, R.drawable.minus_6)
        EasyDiaryUtils.changeDrawableIconColor(this, config.primaryColor, R.drawable.plus_6)
    }

    private fun bindEvent() {
        primaryColor.setOnClickListener(mOnClickListener)
        fontSetting.setOnClickListener(mOnClickListener)
        thumbnailSetting.setOnClickListener(mOnClickListener)
        sensitiveOption.setOnClickListener(mOnClickListener)
        addTtfFontSetting.setOnClickListener(mOnClickListener)
        appLockSetting.setOnClickListener(mOnClickListener)
        rateAppSetting.setOnClickListener(mOnClickListener)
        licenseView.setOnClickListener(mOnClickListener)
        releaseNotes.setOnClickListener(mOnClickListener)
        boldStyleOption.setOnClickListener(mOnClickListener)
        multiPickerOption.setOnClickListener(mOnClickListener)
        fingerprint.setOnClickListener(mOnClickListener)
        enableCardViewPolicy.setOnClickListener(mOnClickListener)
        decreaseFont.setOnClickListener(mOnClickListener)
        increaseFont.setOnClickListener(mOnClickListener)
        contentsSummary.setOnClickListener(mOnClickListener)

        fontLineSpacing.configBuilder
                .min(0.2F)
                .max(1.8F)
                .progress(config.lineSpacingScaleFactor)
                .floatType()
                .sectionCount(16)
                .sectionTextInterval(2)
                .showSectionText()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .autoAdjustSectionMark()
                .build()


        val bubbleSeekBarListener = object : BubbleSeekBar.OnProgressChangedListener {
            override fun onProgressChanged(bubbleSeekBar: BubbleSeekBar?, progress: Int, progressFloat: Float, fromUser: Boolean) {
                Log.i("progress", "$progress $progressFloat")
                config.lineSpacingScaleFactor = progressFloat
                setFontsStyle()
                Log.i("progress", "${config.lineSpacingScaleFactor}")
            }
            override fun getProgressOnActionUp(bubbleSeekBar: BubbleSeekBar?, progress: Int, progressFloat: Float) {}
            override fun getProgressOnFinally(bubbleSeekBar: BubbleSeekBar?, progress: Int, progressFloat: Float, fromUser: Boolean) {}
        }
        fontLineSpacing.setOnProgressChangedListener(bubbleSeekBarListener)
    }

    override fun onResume() {
        super.onResume()
        initPreference()
        setFontsStyle()
        setupInvite()
        
        if (BaseConfig(this).isThemeChanged) {
            BaseConfig(this).isThemeChanged = false
            val readDiaryIntent = Intent(this, DiaryMainActivity::class.java)
            readDiaryIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(readDiaryIntent)
            this.overridePendingTransition(0, 0)
        }
    }

    private fun setupInvite() {
        inviteSummary.text = String.format(getString(R.string.invite_friends_summary), getString(R.string.app_name))
        invite.setOnClickListener {
            val text = String.format(getString(io.github.aafactory.commons.R.string.share_text), getString(R.string.app_name), getStoreUrl())
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
                startActivity(Intent.createChooser(this, getString(io.github.aafactory.commons.R.string.invite_via)))
            }
        }
    }

    private fun openGuideView() {
        val guideIntent = Intent(this, WebViewActivity::class.java)
        guideIntent.putExtra(BaseWebViewActivity.OPEN_URL_INFO, getString(R.string.add_ttf_fonts_info_url))
        startActivity(guideIntent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        pauseLock()
        when (requestCode) {
            REQUEST_CODE_EXTERNAL_STORAGE_WITH_FONT_SETTING -> if (checkPermission(EXTERNAL_STORAGE_PERMISSIONS)) {
                openFontSettingDialog()
            } else {
                makeSnackBar(findViewById(android.R.id.content), getString(R.string.guide_message_3))
            }
            else -> {
            }
        }
    }

    private fun openThumbnailSettingDialog() {
        val builder = AlertDialog.Builder(this@SettingsActivity)
        builder.setNegativeButton(getString(android.R.string.cancel), null)
        builder.setTitle(getString(R.string.thumbnail_setting_title))
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val containerView = inflater.inflate(R.layout.dialog_thumbnail, null)
        val listView = containerView.findViewById<ListView>(R.id.listView)

        var selectedIndex = 0
        val listThumbnailSize = ArrayList<Map<String, String>>()
        for (i in 40..200 step 10) {
            listThumbnailSize.add(mapOf("optionTitle" to "${i}dp x ${i}dp", "size" to "$i"))
        }
        
        listThumbnailSize.mapIndexed { index, map ->
            val size = map["size"] ?: "0"
            if (config.settingThumbnailSize == size.toFloat()) selectedIndex = index
        }
        
        val arrayAdapter = ThumbnailSizeItemAdapter(this@SettingsActivity, R.layout.item_font, listThumbnailSize)
        listView.adapter = arrayAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val fontInfo = parent.adapter.getItem(position) as HashMap<String, String>
            fontInfo["size"]?.let {
                config.settingThumbnailSize = it.toFloat()
            }
            mAlertDialog?.cancel()
        }

        builder.setView(containerView)
        mAlertDialog = builder.create()
        mAlertDialog?.show()
        listView.setSelection(selectedIndex)
    }
    
    private fun openFontSettingDialog() {
        EasyDiaryUtils.initWorkingDirectory(this@SettingsActivity)
        val builder = AlertDialog.Builder(this@SettingsActivity)
        builder.setNegativeButton(getString(android.R.string.cancel), null)
        builder.setTitle(getString(R.string.font_setting))
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val fontView = inflater.inflate(R.layout.dialog_fonts, null)
        val listView = fontView.findViewById<ListView>(R.id.listFont)

        val fontNameArray = resources.getStringArray(R.array.pref_list_fonts_title)
        val fontPathArray = resources.getStringArray(R.array.pref_list_fonts_values)
        val listFont = ArrayList<Map<String, String>>()
        var selectedIndex = 0
        for (i in fontNameArray.indices) {
            val map = HashMap<String, String>()
            map.put("disPlayFontName", fontNameArray[i])
            map.put("fontName", fontPathArray[i])
            listFont.add(map)
        }

        val fontDir = File(Environment.getExternalStorageDirectory().absolutePath + USER_CUSTOM_FONTS_DIRECTORY)
        fontDir.list()?.let {
            for (fontName in it) {
                if (FilenameUtils.getExtension(fontName).equals("ttf", ignoreCase = true)) {
                    val map = HashMap<String, String>()
                    map.put("disPlayFontName", FilenameUtils.getBaseName(fontName))
                    map.put("fontName", fontName)
                    listFont.add(map)
                }
            }
        }
        
        listFont.mapIndexed { index, map ->
            if (config.settingFontName == map["fontName"]) selectedIndex = index
        } 
        
        val arrayAdapter = FontItemAdapter(this@SettingsActivity, R.layout.item_font, listFont)
        listView.adapter = arrayAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val fontInfo = parent.adapter.getItem(position) as HashMap<String, String>
            fontInfo["fontName"]?.let { 
                config.settingFontName = it
                FontUtils.setCommonTypeface(this@SettingsActivity, assets)
                initPreference()
                setFontsStyle()
            }
            mAlertDialog?.cancel()
        }

        builder.setView(fontView)
        mAlertDialog = builder.create()
        mAlertDialog?.show()
        listView.setSelection(selectedIndex)
    }

    private fun setFontsStyle() {
        FontUtils.setFontsTypeface(applicationContext, assets, null, findViewById<ViewGroup>(android.R.id.content))
    }

    private fun initPreference() {
        fontSettingSummary.text = FontUtils.fontFileNameToDisplayName(applicationContext, config.settingFontName)
        sensitiveOptionSwitcher.isChecked = config.diarySearchQueryCaseSensitive
        appLockSettingSwitcher.isChecked = config.aafPinLockEnable
        rateAppSettingSummary.text = String.format("Easy Diary v %s", BuildConfig.VERSION_NAME)
        boldStyleOptionSwitcher.isChecked = config.boldStyleEnable
        multiPickerOptionSwitcher.isChecked = config.multiPickerEnable
        fingerprintSwitcher.isChecked = config.fingerprintLockEnable
        enableCardViewPolicySwitcher.isChecked = config.enableCardViewPolicy
        contentsSummarySwitcher.isChecked = config.enableContentsSummary
    }

    private fun getStoreUrl() = "https://play.google.com/store/apps/details?id=$packageName"

    companion object {
        private var mTaskFlag = 0
    }
}