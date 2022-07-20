package com.example.wallpaper_gallery.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.wallpaper_gallery.R
import com.example.wallpaper_gallery.data.network.ApiFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {

            val response = ApiFactory.apiService.getTopicsPhotos("BJJMtteDJA4")
            Log.d("TEST_OF_LOADING_DATA", response.toString())

        }
    }
}
