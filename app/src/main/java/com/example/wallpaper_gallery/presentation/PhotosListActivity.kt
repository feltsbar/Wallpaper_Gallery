package com.example.wallpaper_gallery.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.presentation.adapters.PhotosAdapter
import kotlinx.android.synthetic.main.activity_photos_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotosListActivity : AppCompatActivity() {

    private lateinit var viewModel: TopicsViewModel
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_list)

        val adapter = PhotosAdapter()
        rv_photos_list.adapter = adapter
        viewModel = ViewModelProvider(this)[TopicsViewModel::class.java]
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

        fun newIntent(context: Context, topicId: String): Intent {
            val intent = Intent(context, PhotosListActivity::class.java)
            intent.putExtra(TOPIC_ID, topicId)
            return intent
        }
    }
}