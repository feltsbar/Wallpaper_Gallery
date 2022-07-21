package com.example.wallpaper_gallery.data.repository

import android.util.Log
import com.example.wallpaper_gallery.data.network.ApiFactory
import com.example.wallpaper_gallery.domain.PhotoInfo
import com.example.wallpaper_gallery.domain.TopicInfo
import com.example.wallpaper_gallery.domain.TopicRepository

class TopicRepositoryImpl : TopicRepository {

    private val apiService = ApiFactory.apiService

    override suspend fun getTopicList(): List<TopicInfo> {
        return try {
            apiService.getTopicsList(itemsPerPage = PARAMS_ITEMS_PER_PAGE).map {
                TopicInfo(topicId = it.topicId, title = it.title, totalPhotos = it.totalPhotos)
            }
        } catch (e: Exception) {
            Log.d("MyLogException", "Failed to load TOPICS list")
            emptyList()
        }
    }

    override suspend fun getPhotoList(topicId: String): List<PhotoInfo> {
        return try {
            apiService.getTopicsPhotos(topicId).map {
                PhotoInfo(photoUrl = it.photoUrls.regular.toString())
            }
        } catch (e: Exception) {
            Log.d("MyLogException", "Failed to load PHOTOS list")
            emptyList()
        }
    }

    companion object {
        private const val PARAMS_ITEMS_PER_PAGE = 25
    }
}