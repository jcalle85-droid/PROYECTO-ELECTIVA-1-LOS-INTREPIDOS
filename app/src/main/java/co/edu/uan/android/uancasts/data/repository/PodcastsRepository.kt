package co.edu.uan.android.uancasts.data.repository

import co.edu.uan.android.uancasts.data.model.PodcastResponse

interface PodcastsRepository {
    suspend fun getPodcasts(): PodcastResponse
}