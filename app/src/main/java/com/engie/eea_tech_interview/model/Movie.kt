package com.engie.eea_tech_interview.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("video") val hasVideo: Boolean?,
)