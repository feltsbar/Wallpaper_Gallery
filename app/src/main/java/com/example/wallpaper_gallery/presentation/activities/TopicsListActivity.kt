package com.example.wallpaper_gallery.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.domain.TopicInfo
import com.example.wallpaper_gallery.presentation.adapters.TopicsAdapter
import com.example.wallpaper_gallery.presentation.view_models.MainViewModel
import kotlinx.android.synthetic.main.activity_topics_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopicsListActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics_list)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = TopicsAdapter()
        rv_topics_list.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        scope.launch {
            val list = viewModel.loadTopics()
            runOnUiThread {
                adapter.submitList(list)
            }
        }
        adapter.onTopicClickListener = object : TopicsAdapter.OnTopicClickListener {
            override fun onTopicClick(topicInfo: TopicInfo) {
                val intent = PhotosListActivity.newIntent(this@TopicsListActivity, topicInfo)
                startActivity(intent)
            }
        }
    }

}
