package com.example.wallpaper_gallery.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.wallpaper_gallery.domain.TopicInfo

object TopicDiffCallback: DiffUtil.ItemCallback<TopicInfo>() {
    override fun areItemsTheSame(oldItem: TopicInfo, newItem: TopicInfo): Boolean {
        return oldItem.topicId == newItem.topicId
    }

    override fun areContentsTheSame(oldItem: TopicInfo, newItem: TopicInfo): Boolean {
        return oldItem == newItem
    }
}