package com.example.wallpaper_gallery.domain

import androidx.lifecycle.LiveData

interface TopicRepository {
    fun fetTopicList() : LiveData<List<TopicInfo>>
    fun fetPhotoList(topicId : String) : LiveData<List<PhotoInfo>>
}