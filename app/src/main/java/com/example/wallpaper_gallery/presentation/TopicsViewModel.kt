package com.example.wallpaper_gallery.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper_gallery.data.repository.TopicRepositoryImpl
import com.example.wallpaper_gallery.domain.GetPhotoListUseCase
import com.example.wallpaper_gallery.domain.GetTopicListUseCase
import com.example.wallpaper_gallery.domain.TopicInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TopicsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TopicRepositoryImpl()
    private val getTopicList = GetTopicListUseCase(repository)
    private val getPhotoList = GetPhotoListUseCase(repository)

    var topicList = mutableListOf<TopicInfo>()
//    val tiptoe = listOf(TopicInfo("gheriuhg", "ttt", 12))

    init {
        viewModelScope.launch {
            topicList = loadTopics() as MutableList<TopicInfo>
        }
    }

    suspend fun loadTopics() : List<TopicInfo> {
        Log.d("TEST_OF_LOADING_DATA", getTopicList.invoke().toString())
        return getTopicList.invoke()
    }

    private fun loadPhotosByTopic(topicId: String) {
        viewModelScope.launch {
            Log.d("TEST_OF_LOADING_DATA", getPhotoList.invoke(topicId).toString())
        }
    }

}