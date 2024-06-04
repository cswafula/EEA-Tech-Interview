package com.engie.eea_tech_interview.network.repositories

import com.engie.eea_tech_interview.model.GenreResult
import com.engie.eea_tech_interview.model.SearchResult
import com.engie.eea_tech_interview.network.api.MovieApiService
import com.engie.eea_tech_interview.network.apiRepository.MoviesApiRepository
import retrofit2.Response

class MoviesRepository(private val movieApiService: MovieApiService): MoviesApiRepository {

    companion object {
        const val MOVIE_API_KEY = "47304f18bd4a3b4e733196b18e68bfbc"
        const val SEARCH_QUERY = "James Bond"
    }

    override suspend fun searchMovies(query: String): Response<SearchResult> {
        return movieApiService.getMovies(
            apiKey = MOVIE_API_KEY,
            query = query
        )
    }

    override suspend fun getGenres(): Response<GenreResult> {
        TODO("Not yet implemented")
    }


}