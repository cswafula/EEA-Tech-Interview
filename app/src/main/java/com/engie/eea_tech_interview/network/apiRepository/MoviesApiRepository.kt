package com.engie.eea_tech_interview.network.apiRepository

import com.engie.eea_tech_interview.model.GenreResult
import com.engie.eea_tech_interview.model.SearchResult
import retrofit2.Response

interface MoviesApiRepository {

    suspend fun searchMovies(query: String) : Response<SearchResult>

    suspend fun getGenres(): Response<GenreResult>
}