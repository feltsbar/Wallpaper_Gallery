package com.example.wallpaper_gallery.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper_gallery.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TopicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[TopicsViewModel::class.java]
        viewModel.loadTopics()
    }
}
