package com.example.wallpaper_gallery.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.domain.TopicInfo
import com.example.wallpaper_gallery.presentation.view_models.MainViewModel
import com.example.wallpaper_gallery.presentation.adapters.PhotosAdapter
import kotlinx.android.synthetic.main.activity_photos_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotosListActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_list)

        title = intent.getStringExtra(TOPIC_TITLE)
        val adapter = PhotosAdapter()
        rv_photos_list.adapter = adapter
        gridLayoutManager = GridLayoutManager(
            applicationContext,
            2,
            LinearLayoutManager.VERTICAL,
            false
        )
        rv_photos_list.layoutManager = gridLayoutManager
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val topic = intent.getStringExtra(TOPIC_ID) ?: ""
        scope.launch {
            val list = viewModel.loadPhotosByTopic(topic)
            runOnUiThread {
                adapter.submitList(list)
            }
        }
        adapter.onPhotoClickListener = object : PhotosAdapter.OnPhotoClickListener {
            override fun onPhotoClick(imageUrl: String) {
                Log.d("ON_PHOTO_CLICK", imageUrl)
            }

        }
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