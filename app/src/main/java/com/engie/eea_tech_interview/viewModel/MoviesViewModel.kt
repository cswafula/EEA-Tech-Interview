package com.engie.eea_tech_interview.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engie.eea_tech_interview.network.api.MovieApiService
import com.engie.eea_tech_interview.network.repositories.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesViewModel() : ViewModel(), KoinComponent {

    private val moviesRepository: MoviesRepository by inject()

    fun searchMovies(){
        viewModelScope.launch {
            val resp = moviesRepository.searchMovies("James Bond")
            Log.e("searchMovies: ", "${resp.body()}")
        }
    }

}