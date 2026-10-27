package co.edu.uan.android.uancasts.data.model

import com.google.gson.annotations.SerializedName

data class Podcast(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("publisher")
    val publisher: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("image")
    val image: String = "",

    @SerializedName("thumbnail")
    val thumbnail: String = "",

    @SerializedName("total_episodes")
    val totalEpisodes: Int = 0,

    @SerializedName("listennotes_url")
    val listenNotesUrl: String = ""
)
