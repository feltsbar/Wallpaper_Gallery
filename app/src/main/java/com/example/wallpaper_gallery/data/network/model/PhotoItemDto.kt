package com.example.wallpaper_gallery.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoItemDto(
    @SerializedName("id")
    @Expose
    val photoId: String,

    @SerializedName("urls")
    @Expose
    val photoUrlsDto: PhotoUrlsDto
)