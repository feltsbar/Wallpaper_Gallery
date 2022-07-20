package com.example.wallpaper_gallery.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper_gallery.data.network.ApiFactory
import kotlinx.coroutines.launch

class TopicsViewModel(application: Application) : AndroidViewModel(application) {

    fun loadTopics() {
        viewModelScope.launch {
            val topicsList = ApiFactory.apiService.getTopicsList(25)
            Log.d("TEST_OF_LOADING_DATA", topicsList.toString())
        }

    }


}