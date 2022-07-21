package com.example.wallpaper_gallery.data.repository

import com.example.wallpaper_gallery.data.network.ApiFactory
import com.example.wallpaper_gallery.domain.PhotoInfo
import com.example.wallpaper_gallery.domain.TopicInfo
import com.example.wallpaper_gallery.domain.TopicRepository

class TopicRepositoryImpl : TopicRepository {

    private val apiService = ApiFactory.apiService

    override suspend fun getTopicList(): List<TopicInfo> {
        return apiService.getTopicsList(itemsPerPage = PARAMS_ITEMS_PER_PAGE).map {
            TopicInfo(topicId = it.topicId, title = it.title, totalPhotos = it.totalPhotos)
        }
    }

    override suspend fun getPhotoList(topicId: String): List<PhotoInfo> {
        return apiService.getTopicsPhotos(topicId).map {
            PhotoInfo(photoUrl = it.photoUrls.regular.toString())
        }
    }

    companion object {
        private const val PARAMS_ITEMS_PER_PAGE = 25
    }
}