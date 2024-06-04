package com.engie.eea_tech_interview.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.engie.eea_tech_interview.ui.screens.MovieDetailsScreen
import com.engie.eea_tech_interview.ui.screens.MoviesScreen
import kotlinx.serialization.Serializable

@Composable
fun MoviesNavGraph(
    modifier: Modifier = Modifier.fillMaxHeight(),
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Movies
    ) {

        composable<Movies> {
            MoviesScreen(navigation = navController)
        }

        composable<MovieDetails> {
            val args = it.toRoute<MovieDetails>()
            MovieDetailsScreen(
                args = args,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Serializable
object Movies

@Serializable
data class MovieDetails(
    val movieName: String
)