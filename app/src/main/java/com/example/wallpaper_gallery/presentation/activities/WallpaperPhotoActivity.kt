package com.example.wallpaper_gallery.presentation.activities

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wallpaper_gallery.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_wallpaper_photo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WallpaperPhotoActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper_photo)

        val photosUrl = intent.getStringExtra(PHOTO_URL)
        Picasso.get().load(photosUrl).into(iv_wallpaper)
        btn_set_as_wallpaper.setOnClickListener {
            scope.launch {
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                val result: Bitmap = Picasso.get().load(photosUrl).get()
                try {
                    wallpaperManager.setBitmap(result)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            Toast.makeText(this, "The wallpaper has been changed", Toast.LENGTH_SHORT).show()

        }

    }

    companion object {
        private const val PHOTO_URL = "PHOTO_URL"

        fun newIntent(context: Context, photoUrl: String): Intent {
            val intent = Intent(context, WallpaperPhotoActivity::class.java)
            intent.putExtra(PHOTO_URL, photoUrl)
            return intent
        }
    }
}