package com.example.wallpaper_gallery.domain

class GetPhotoListUseCase(private val repository: TopicRepository) {
    suspend operator fun invoke(topicId : String) = repository.getPhotoList(topicId)
}