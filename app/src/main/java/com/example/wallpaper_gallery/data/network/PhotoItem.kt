package com.example.wallpaper_gallery.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoItem(
    @SerializedName("id")
    @Expose
    val photoId : Int,

    @SerializedName("urls")
    @Expose
    val photoUrlsList : List<PhotoUrls>? = null
)