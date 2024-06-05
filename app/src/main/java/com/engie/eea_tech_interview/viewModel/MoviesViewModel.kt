package com.engie.eea_tech_interview.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.model.SearchResult
import com.engie.eea_tech_interview.network.api.MovieApiService
import com.engie.eea_tech_interview.network.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

@OptIn(FlowPreview::class)
class MoviesViewModel() : BaseViewModel(), KoinComponent {

    private val moviesRepository: MoviesRepository by inject()

    val moviesPage = 1

    private var _movieListState = MutableStateFlow(listOf<Movie>())
    val movieList = _movieListState.asStateFlow()

    private val _searchMoviesText = MutableStateFlow("")
    val searchMovie = _searchMoviesText.asStateFlow()

    val searchMovieResults = searchMovie
        .onEach { uiState.value = LoadingState.ShowLoading(true) }
        .debounce(3000L)
        .combine(_movieListState){ text, movies ->
            if(text.isBlank()){
                movies
            }else{
                viewModelScope.launch {
                    withContext(Dispatchers.IO){
                        _movieListState.value = moviesRepository.searchMovies(text).body()?.results ?: emptyList()
                    }
                }
                _movieListState.value
            }
        }
            .onEach { uiState.value = LoadingState.ShowLoading(false) }
            .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(0),
            _movieListState.value)

    init {
        getPopularMovies()
    }

    fun onMovieSearch(text: String){
        _searchMoviesText.value = text
    }

    fun refreshPopularMoviesList(){
        _searchMoviesText.value = ""
        getPopularMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            uiState.value = LoadingState.ShowLoading(true)

            moviesRepository.getMoviesList(moviesPage).collectLatest {
                when(it){
                    is LoadingState.Error -> {
                        uiState.value = LoadingState.Error(it.message ?: "Error")
                    }
                    is LoadingState.Success -> {
                        it.data?.let { data->
                            _movieListState.value = data.shuffled()
                        }
                        moviesPage+1
                        uiState.value = LoadingState.Success(movieList.value)
                    }
                    is LoadingState.ShowLoading -> {
                        uiState.value = LoadingState.ShowLoading(it.isLoading)
                    }
                }
            }
        }
    }

    private fun searchMovies(query: String) {

        viewModelScope.launch {
            _movieListState.value = moviesRepository.searchMovies(query).body()?.results ?: emptyList()
        }
    }

    fun paginateList(){
        uiState.value = LoadingState.ShowLoading(true)
    }

}