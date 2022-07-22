package com.example.wallpaper_gallery.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaper_gallery.databinding.ActivityPhotosListBinding
import com.example.wallpaper_gallery.domain.TopicInfo
import com.example.wallpaper_gallery.presentation.adapters.PhotosAdapter
import com.example.wallpaper_gallery.presentation.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotosListActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private val scope = CoroutineScope(Dispatchers.IO)
    private val binding by lazy {
        ActivityPhotosListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = intent.getStringExtra(TOPIC_TITLE)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = PhotosAdapter()
        binding.rvPhotosList.adapter = adapter
        gridLayoutManager = GridLayoutManager(
            applicationContext,
            2,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvPhotosList.layoutManager = gridLayoutManager
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val topic = intent.getStringExtra(TOPIC_ID) ?: ""
        scope.launch {
            val list = viewModel.loadPhotosByTopic(topic)
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
        adapter.onPhotoClickListener = object : PhotosAdapter.OnPhotoClickListener {
            override fun onPhotoClick(imageUrl: String) {
                val intent = WallpaperPhotoActivity.newIntent(this@PhotosListActivity, imageUrl)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    companion object {
        private const val TOPIC_ID = "TOPIC_ID"
        private const val TOPIC_TITLE = "TOPIC_TITLE"

        fun newIntent(context: Context, topicInfo: TopicInfo): Intent {
            val intent = Intent(context, PhotosListActivity::class.java)
            intent.putExtra(TOPIC_ID, topicInfo.topicId)
            intent.putExtra(TOPIC_TITLE, topicInfo.title)
            return intent
        }
    }
}