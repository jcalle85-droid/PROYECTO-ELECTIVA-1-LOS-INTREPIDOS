package co.edu.uan.android.uancasts.data.model

import com.google.gson.annotations.SerializedName

data class PodcastResponse(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("total")
    val total: Int = 0,

    @SerializedName("results")
    val results: List<Podcast> = emptyList()
)
