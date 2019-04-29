package me.blog.korn123.easydiary.adapters

import android.app.Activity
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import io.realm.RealmList
import me.blog.korn123.easydiary.R
import me.blog.korn123.easydiary.models.PhotoUriDto
import me.blog.korn123.easydiary.viewholders.PhotoViewHolder

class PhotoAdapter(
        val activity: Activity,
        val photoUris: RealmList<PhotoUriDto>
) : RecyclerView.Adapter<PhotoViewHolder>() {
    private val glideOptionMap = hashMapOf<Int, Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_photo, parent, false)
        return PhotoViewHolder(view, activity, itemCount)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        photoUris[position]?.let { photoUri ->
            holder.itemView.setOnClickListener { _ ->
                when (glideOptionMap[position]) {
                    null -> glideOptionMap[position] = 1
                    else -> glideOptionMap[position] = glideOptionMap[position]?.plus(1) ?: 0
                }
                holder.bindTo(photoUri.getFilePath(), position, glideOptionMap[position]?.rem(9) ?: 0)
            }

            holder.bindTo(photoUri.getFilePath(), position)
        }
    }

    override fun getItemCount() = photoUris.size
    
    fun getFlexDirection(): Int = when (activity.resources.configuration.orientation == ORIENTATION_PORTRAIT) {
        true -> {
            when (itemCount) {
                3, 5, 6 -> FlexDirection.COLUMN
                else -> FlexDirection.ROW    
            }
        }
        false -> FlexDirection.COLUMN
    }
}
