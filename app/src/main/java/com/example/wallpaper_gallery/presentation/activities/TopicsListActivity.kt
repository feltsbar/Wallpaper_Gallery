package com.example.wallpaper_gallery.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper_gallery.databinding.ActivityTopicsListBinding
import com.example.wallpaper_gallery.domain.TopicInfo
import com.example.wallpaper_gallery.presentation.adapters.TopicsAdapter
import com.example.wallpaper_gallery.presentation.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopicsListActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val scope = CoroutineScope(Dispatchers.IO)
    private val binding by lazy {
        ActivityTopicsListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        title = "Thematics"
    }

    private fun setupRecyclerView() {
        val adapter = TopicsAdapter()
        binding.rvTopicsList.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        scope.launch {
            val list = viewModel.loadTopics()
            if (list.isEmpty()) {
                with(binding) {
                    tvNoInternet.visibility = View.VISIBLE
                    ivNoInternet.visibility = View.VISIBLE
                }
            } else {
                with(binding) {
                    tvNoInternet.visibility = View.GONE
                    ivNoInternet.visibility = View.GONE
                }

                runOnUiThread {
                    adapter.submitList(list)
                }
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
