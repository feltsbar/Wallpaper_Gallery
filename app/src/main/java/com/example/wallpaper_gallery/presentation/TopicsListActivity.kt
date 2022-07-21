package com.example.wallpaper_gallery.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper_gallery.R

class TopicsListActivity : AppCompatActivity() {

    private lateinit var viewModel: TopicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics_list)

//        val adapter = TopicsAdapter()
//        rv_topics_list.adapter = adapter
        viewModel = ViewModelProvider(this)[TopicsViewModel::class.java]
//        ApiFactory.apiService.getTopicsList().subscribeOn(Schedulers.io())
    }
}
