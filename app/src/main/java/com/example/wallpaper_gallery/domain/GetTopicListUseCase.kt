package com.example.wallpaper_gallery.domain

class GetTopicListUseCase(private val repository: TopicRepository) {
    suspend operator fun invoke() = repository.getTopicList()
}