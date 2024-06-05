package com.engie.eea_tech_interview.network.api

import com.engie.eea_tech_interview.model.GenreResult
import com.engie.eea_tech_interview.model.MoviesList
import com.engie.eea_tech_interview.model.SearchResult
import com.engie.eea_tech_interview.network.NetworkUtils
import com.engie.eea_tech_interview.network.URLS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(URLS.SEARCH_MOVIE)
    suspend fun getMovies(
        @Query("api_key") apiKey: String = NetworkUtils.MOVIE_API_KEY,
        @Query("query") query: String,
    ): Response<SearchResult>

    @GET(URLS.MOVIE_GENRES)
    suspend fun getGenre(
        @Query("api_key") apiKey: String = NetworkUtils.MOVIE_API_KEY
    ): Response<GenreResult>

    @GET(URLS.MOVIES_LIST)
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String = NetworkUtils.MOVIE_API_KEY,
        @Query("page") page: Int
    ): MoviesList
}