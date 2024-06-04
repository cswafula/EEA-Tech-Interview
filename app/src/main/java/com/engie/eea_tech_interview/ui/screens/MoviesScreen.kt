package com.engie.eea_tech_interview.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.engie.eea_tech_interview.navigation.MovieDetails
import com.engie.eea_tech_interview.viewModel.MoviesViewModel

@Composable
fun MoviesScreen(navigation: NavHostController) {

    val viewModel = viewModel<MoviesViewModel>()

//    navigation.navigate(MovieDetailsScreen(
//        ""
//    ))

    viewModel.searchMovies()

    Column(Modifier.fillMaxSize()) {



    }
}