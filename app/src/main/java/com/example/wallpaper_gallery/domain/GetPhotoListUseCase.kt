package com.example.wallpaper_gallery.domain

class GetPhotoListUseCase(private val repository: TopicRepository) {
    operator fun invoke(topicId : String) = repository.fetPhotoList()
}