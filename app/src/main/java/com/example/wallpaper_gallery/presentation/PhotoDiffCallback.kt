package com.example.wallpaper_gallery.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.wallpaper_gallery.domain.PhotoInfo

object PhotoDiffCallback: DiffUtil.ItemCallback<PhotoInfo>() {
    override fun areItemsTheSame(oldItem: PhotoInfo, newItem: PhotoInfo): Boolean {
        return oldItem.photoUrl == newItem.photoUrl
    }

    override fun areContentsTheSame(oldItem: PhotoInfo, newItem: PhotoInfo): Boolean {
        return oldItem == newItem
    }
}