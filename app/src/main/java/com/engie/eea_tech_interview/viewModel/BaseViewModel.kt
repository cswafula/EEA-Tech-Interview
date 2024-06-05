package com.engie.eea_tech_interview.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

open class BaseViewModel() : ViewModel() {

    val uiState = mutableStateOf<LoadingState<Any>>(LoadingState.ShowLoading(false))

    fun defaultState(){
        uiState.value = LoadingState.ShowLoading(false)
    }
}

sealed class LoadingState<T>(
    val data: T?,
    val message: String? = null
) {
    class ShowLoading<T>(val isLoading: Boolean = true) : LoadingState<T>(null)
    class Error<T>(error: String, data: T? = null) : LoadingState<T>(data, error)
    class Success<T>(data: T?) : LoadingState<T>(data)
}