package com.example.wallpaper_gallery.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.domain.TopicInfo
import com.example.wallpaper_gallery.presentation.TopicDiffCallback
import kotlinx.android.synthetic.main.item_topic.view.*

class TopicsAdapter : ListAdapter<TopicInfo, TopicsAdapter.TopicViewHolder>(TopicDiffCallback) {

    var onTopicClickListener : OnTopicClickListener? = null

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = getItem(position)
        with(holder) {
            tvTopicsTitle.text = topic.title
            tvTopicsPhotoCount.text = topic.totalPhotos.toString()
            itemView.setOnClickListener {
                onTopicClickListener?.onTopicClick(topic.topicId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)
        return TopicViewHolder(view)
    }


    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTopicsTitle = itemView.tv_topics_title
        val tvTopicsPhotoCount = itemView.tv_topics_photo_count
    }

    interface OnTopicClickListener {
        fun onTopicClick(topicId: String)
    }

}
