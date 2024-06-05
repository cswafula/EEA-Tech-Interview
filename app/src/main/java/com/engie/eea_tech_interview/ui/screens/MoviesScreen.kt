package com.engie.eea_tech_interview.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.model.mappers.toMovieNavigation
import com.engie.eea_tech_interview.ui.widgets.CustomLoader
import com.engie.eea_tech_interview.ui.widgets.ErrorHandleView
import com.engie.eea_tech_interview.ui.widgets.OutlinedSearchEditText
import com.engie.eea_tech_interview.ui.widgets.TextTitle
import com.engie.eea_tech_interview.ui.widgets.VerticalSpacer
import com.engie.eea_tech_interview.viewModel.LoadingState
import com.engie.eea_tech_interview.viewModel.MoviesViewModel

@Composable
fun MoviesScreen(navigation: NavHostController) {

    val viewModel = viewModel<MoviesViewModel>()
    val moviesList = viewModel.movieList.collectAsState()
    val searchMovieText = viewModel.searchMovie.collectAsState()
    val searchMovieResults = viewModel.searchMovieResults.collectAsState()


    val showSearchView = remember {
        mutableStateOf(false)
    }

    when (val uiState = viewModel.uiState.value) {
        is LoadingState.Success -> {}
        is LoadingState.Error -> {
            ErrorHandleView(message = uiState.message ?: "Error",
                onDismiss = {})
        }

        is LoadingState.ShowLoading -> {
            if (uiState.isLoading) {
                CustomLoader()
            }
        }
    }

    fun onSearchClicked() {
        showSearchView.value = true
    }

    fun onCancelSearch() {
        showSearchView.value = false
        viewModel.refreshPopularMoviesList()
    }

    fun movieCardClicked(movie: Movie) {
        navigation.navigate(movie.toMovieNavigation())
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .testTag("Main Column")
    ) {

        AnimatedVisibility(!showSearchView.value) {
            Row(
                Modifier.fillMaxWidth()
                    .testTag("Default Row"),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextTitle(title = stringResource(id = R.string.popular_movies))

                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable(onClick = ::onSearchClicked)
                )
            }
        }

        AnimatedVisibility(visible = showSearchView.value) {
            SearchMoviesView(
                searchString = searchMovieText.value,
                onSearchString = viewModel::onMovieSearch,
                cancelSearch = ::onCancelSearch
            )
        }

        VerticalSpacer()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag("Grid Layout"),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            val movies = if (showSearchView.value) searchMovieResults.value else moviesList.value

            items(
                items = movies,
                key = {
                    it.id
                }
            ) {
                MovieCard(
                    movie = it,
                    onCardClicked = ::movieCardClicked
                )
            }

        }


    }
}

@Composable
fun SearchMoviesView(
    searchString: String,
    onSearchString: (char: String) -> Unit,
    cancelSearch: () -> Unit
) {

    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            Modifier.fillMaxWidth()
                .testTag("Search Row"),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextTitle(title = stringResource(id = R.string.search_movie))

            Icon(
                imageVector = Icons.Outlined.Cancel,
                contentDescription = "Cancel Icon",
                modifier = Modifier
                    .size(25.dp)
                    .clickable(onClick = { cancelSearch() })
                    .testTag("Cancel Icon")
            )
        }


        VerticalSpacer(8.dp)

        OutlinedSearchEditText(
            label = stringResource(id = R.string.search_movie),
            searchString = searchString,
            onStringChange = { onSearchString(it) }
        )
    }
}
