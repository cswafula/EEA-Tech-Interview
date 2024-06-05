package com.engie.eea_tech_interview.network.repositories

import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.model.SearchResult
import com.engie.eea_tech_interview.network.api.MovieApiService
import com.engie.eea_tech_interview.network.apiRepository.MoviesApiRepository
import com.engie.eea_tech_interview.viewModel.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class MoviesRepository(private val movieApiService: MovieApiService): MoviesApiRepository {

    override suspend fun searchMovies(query: String): Response<SearchResult> {
        return movieApiService.getMovies(
            query = query
        )
    }

    override suspend fun getMoviesList(page: Int): Flow<LoadingState<List<Movie>>> {
        return flow {
            emit(LoadingState.ShowLoading(true))

            val movieList = movieApiService.getMoviesList(page = page)

            emit(LoadingState.Success(movieList.results))

            emit(LoadingState.ShowLoading(false))

        }.catch {
            emit(LoadingState.Error(error = "Error Loading Movies"))
        }
    }

}