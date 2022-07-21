package com.example.wallpaper_gallery.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.domain.TopicInfo
import kotlinx.android.synthetic.main.activity_topics_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopicsListActivity : AppCompatActivity() {

    private lateinit var viewModel: TopicsViewModel
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics_list)

        val adapter = TopicsAdapter()
        rv_topics_list.adapter = adapter
        viewModel = ViewModelProvider(this)[TopicsViewModel::class.java]
        scope.launch {
            val list = viewModel.loadTopics()
            runOnUiThread {
                adapter.submitList(list)
            }
        }
        adapter.onTopicClickListener = object : TopicsAdapter.OnTopicClickListener {
            override fun onTopicClick(topicId: String) {
                Log.d("Click_Info", topicId)
            }

        }

    }
}
