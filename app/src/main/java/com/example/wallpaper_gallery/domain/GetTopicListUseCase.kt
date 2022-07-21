package com.example.wallpaper_gallery.domain

class GetTopicListUseCase(private val repository: TopicRepository) {
    operator fun invoke() = repository.fetTopicList()
}