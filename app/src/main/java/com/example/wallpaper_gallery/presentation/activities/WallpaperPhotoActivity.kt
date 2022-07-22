package com.example.wallpaper_gallery.presentation.activities

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wallpaper_gallery.databinding.ActivityWallpaperPhotoBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WallpaperPhotoActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val binding by lazy {
        ActivityWallpaperPhotoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val photosUrl = intent.getStringExtra(PHOTO_URL)
        if (photosUrl == EMPTY_URL) {
            with(binding) {
                tvNoInternet.visibility = View.VISIBLE
                ivNoInternet.visibility = View.VISIBLE
                ivWallpaper.visibility = View.GONE
                btnSetAsWallpaper.visibility = View.GONE
            }
        } else {
            with(binding) {
                tvNoInternet.visibility = View.GONE
                ivNoInternet.visibility = View.GONE
                ivWallpaper.visibility = View.VISIBLE
                btnSetAsWallpaper.visibility = View.VISIBLE
                Picasso.get().load(photosUrl).into(ivWallpaper)
                btnSetAsWallpaper.setOnClickListener {
                    scope.launch {
                        val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                        val result: Bitmap = Picasso.get().load(photosUrl).get()
                        try {
                            wallpaperManager.setBitmap(result)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    Toast.makeText(
                        this@WallpaperPhotoActivity,
                        "The wallpaper has been changed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
        private const val PHOTO_URL = "PHOTO_URL"
        private const val EMPTY_URL = ""

        fun newIntent(context: Context, photoUrl: String): Intent {
            val intent = Intent(context, WallpaperPhotoActivity::class.java)
            intent.putExtra(PHOTO_URL, photoUrl)
            return intent
        }
    }
}