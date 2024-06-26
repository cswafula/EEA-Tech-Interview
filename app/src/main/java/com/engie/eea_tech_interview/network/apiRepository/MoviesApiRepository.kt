package com.engie.eea_tech_interview.network.apiRepository

import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.model.SearchResult
import com.engie.eea_tech_interview.viewModel.LoadingState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MoviesApiRepository {

    suspend fun searchMovies(query: String) : Response<SearchResult>
    suspend fun getMoviesList(page: Int) : Flow<LoadingState<List<Movie>>>
}