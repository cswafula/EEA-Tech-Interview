package com.engie.eea_tech_interview.model

data class GenreResult(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)