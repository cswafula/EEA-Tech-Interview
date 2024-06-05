package com.engie.eea_tech_interview.viewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.viewModelScope
import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.network.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(FlowPreview::class)
class MoviesViewModel : BaseViewModel(), KoinComponent {

    private val moviesRepository: MoviesRepository by inject()

    private val moviesPage = mutableIntStateOf(1)

    private var _movieListState = MutableStateFlow(listOf<Movie>())
    val movieList = _movieListState.asStateFlow()

    private var _searchMovieListState = MutableStateFlow(movieList.value)

    private val _searchMoviesText = MutableStateFlow("")
    val searchMovie = _searchMoviesText.asStateFlow()

    val searchMovieResults = searchMovie
        .debounce(1000L)
        .combine(_searchMovieListState) { text, movies ->
            if (text.isBlank()) {
                movies
            } else {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        _searchMovieListState.value =
                            moviesRepository.searchMovies(text).body()?.results ?: emptyList()
                    }
                }
                _searchMovieListState.value
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(0),
            _searchMovieListState.value
        )

    init {
        getPopularMovies()
    }

    fun onMovieSearch(text: String) {
        _searchMoviesText.value = text
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            uiState.value = LoadingState.ShowLoading(true)

            moviesRepository.getMoviesList(moviesPage.intValue).collectLatest {
                when (it) {
                    is LoadingState.Error -> {
                        uiState.value = LoadingState.Error(it.message ?: "Error")
                    }

                    is LoadingState.Success -> {
                        it.data?.let { data ->
                            _movieListState.value = data.shuffled()
                        }
                        moviesPage.intValue += 1
                        uiState.value = LoadingState.Success(movieList.value)
                    }

                    is LoadingState.ShowLoading -> {
                        uiState.value = LoadingState.ShowLoading(it.isLoading)
                    }
                }
            }
        }
    }

    fun getPopularMoviesNextPage() {
        viewModelScope.launch {
            uiState.value = LoadingState.ShowLoading(true)

            moviesRepository.getMoviesList(moviesPage.intValue).collectLatest {
                when (it) {
                    is LoadingState.Error -> {
                        uiState.value = LoadingState.Error(it.message ?: "Error")
                    }

                    is LoadingState.Success -> {
                        it.data?.let { data ->
                            _movieListState.value += data.shuffled()
                        }
                        moviesPage.intValue += 1
                        uiState.value = LoadingState.Success(movieList.value)
                    }

                    is LoadingState.ShowLoading -> {
                        uiState.value = LoadingState.ShowLoading(it.isLoading)
                    }
                }
            }
        }

    }

}