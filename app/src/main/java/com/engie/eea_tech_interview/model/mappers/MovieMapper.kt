package com.engie.eea_tech_interview.model.mappers

import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.navigation.MovieDetails

fun Movie.toMovieNavigation() : MovieDetails {
    return MovieDetails(
        isAdult = adult,
        posterPath = posterPath,
        overview = overview,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage.toString(),
        voteCount = voteCount
    )
}