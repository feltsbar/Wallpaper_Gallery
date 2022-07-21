package com.example.wallpaper_gallery.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.domain.PhotoInfo
import com.example.wallpaper_gallery.presentation.callbacks.PhotoDiffCallback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter : ListAdapter<PhotoInfo, PhotosAdapter.PhotoViewHolder>(PhotoDiffCallback) {

    var onPhotoClickListener: PhotosAdapter.OnPhotoClickListener? = null

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhotoItem = itemView.iv_photo_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosAdapter.PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        with(holder) {
            Picasso.get().load(photo.photoUrl).into(ivPhotoItem)
            itemView.setOnClickListener {
                onPhotoClickListener?.onPhotoClick(photo.photoUrl)
            }
        }
    }

    interface OnPhotoClickListener {
        fun onPhotoClick(imageUrl: String)
    }
}