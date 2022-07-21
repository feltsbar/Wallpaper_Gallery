package com.example.wallpaper_gallery.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.data.network.model.TopicItemDto
import kotlinx.android.synthetic.main.item_topic.view.*

class TopicsAdapter : RecyclerView.Adapter<TopicsAdapter.TopicViewHolder>() {

    var topicList = arrayListOf<TopicItemDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topicList[position]
        with(holder) {
            tvTopicsTitle.text = topic.title
            tvTopicsPhotoCount.text = topic.totalPhotos.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)
        return TopicViewHolder(view)
    }

    override fun getItemCount() = topicList.size

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTopicsTitle = itemView.tv_topics_title
        val tvTopicsPhotoCount = itemView.tv_topics_photo_count
    }
}
