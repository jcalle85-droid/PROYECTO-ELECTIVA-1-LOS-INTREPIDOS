package co.edu.uan.android.uancasts.data.repository

import co.edu.uan.android.uancasts.data.model.PodcastResponse
import co.edu.uan.android.uancasts.data.network.ListenNotesApiService

class ApiPodcastsRepository(
    private val apiService: ListenNotesApiService
) : PodcastsRepository {

    override suspend fun getPodcasts(): PodcastResponse {
        return apiService.bestPodcasts()
    }
}
