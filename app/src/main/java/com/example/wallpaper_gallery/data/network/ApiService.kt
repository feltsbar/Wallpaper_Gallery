package com.example.wallpaper_gallery.data.network

import com.example.wallpaper_gallery.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/topics")
    suspend fun getTopicsList(
        @Query(QUERY_PARAM_ITEMS_PER_PAGE) itemsPerPage : Int = 10
    ): List<TopicItem>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/topics/{id_or_slug}/photos")
    suspend fun getTopicsPhotos(
        @Path("id_or_slug") idOrSlugOfTopic : String,
        @Query(QUERY_PARAM_ITEMS_PER_PAGE) itemsPerPage : Int = 10,
        @Query(QUERY_PARAM_PHOTO_ORIENTATION) photoOrientation : String = PHOTO_ORIENTATION_PORTRAIT
    ): List<PhotoItem>

    companion object {
        private const val QUERY_PARAM_ITEMS_PER_PAGE = "per_page"
        private const val QUERY_PARAM_PHOTO_ORIENTATION = "orientation"

        private const val PHOTO_ORIENTATION_LANDSCAPE = "landscape"
        private const val PHOTO_ORIENTATION_PORTRAIT = "portrait"
        private const val PHOTO_ORIENTATION_SQUARISH = "squarish"
    }
}