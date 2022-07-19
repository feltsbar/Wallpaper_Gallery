package com.example.wallpaper_gallery.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllTopics(
    @SerializedName("id")
    @Expose
    val topicId : Int,

    @SerializedName("title")
    @Expose
    val title : String,

    @SerializedName("total_photos")
    @Expose
    val totalPhotos : Int
)