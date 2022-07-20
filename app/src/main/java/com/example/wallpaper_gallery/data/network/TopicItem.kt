package com.example.wallpaper_gallery.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopicItem(
    @SerializedName("id")
    @Expose
    val topicId: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("total_photos")
    @Expose
    val totalPhotos: Int,
)