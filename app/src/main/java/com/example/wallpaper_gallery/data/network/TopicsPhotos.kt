package com.example.wallpaper_gallery.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopicsPhotos(
    @SerializedName("id")
    @Expose
    val photoId : Int,

    @SerializedName("urls")
    @Expose
    val urlsPhoto : List<UrlsOfPhoto>? = null
)