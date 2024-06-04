package com.engie.eea_tech_interview.network.api

import com.engie.eea_tech_interview.model.GenreResult
import com.engie.eea_tech_interview.model.SearchResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("search/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): Response<SearchResult>

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String
    ): Response<GenreResult>
}