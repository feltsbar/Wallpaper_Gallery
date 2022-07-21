package com.example.wallpaper_gallery.domain

interface TopicRepository {
    suspend fun getTopicList() : List<TopicInfo>
    suspend fun getPhotoList(topicId : String) : List<PhotoInfo>
}