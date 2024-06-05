package com.engie.eea_tech_interview.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.navigation.MovieDetails
import com.engie.eea_tech_interview.network.NetworkUtils
import com.engie.eea_tech_interview.ui.theme.DarkBlue
import com.engie.eea_tech_interview.ui.theme.Grey
import com.engie.eea_tech_interview.ui.theme.Primary
import com.engie.eea_tech_interview.ui.widgets.HorizontalSpacer
import com.engie.eea_tech_interview.ui.widgets.RatingBarView
import com.engie.eea_tech_interview.ui.widgets.TextContent
import com.engie.eea_tech_interview.ui.widgets.TextTitle
import com.engie.eea_tech_interview.ui.widgets.VerticalSpacer

@Composable
fun MovieDetailsScreen(
    args: MovieDetails,
    navigateBack: () -> Boolean
) {

    val posterImageUrl = NetworkUtils.IMAGES_BASE_URL.plus(args.posterPath)
    val movieRating = if(args.isAdult) "R" else "PG"

    fun backNavigation(){
        navigateBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, DarkBlue),
                    endY = 500f
                )
            )
    )

    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())) {

        VerticalSpacer(15.dp)

        Image(
            painter = painterResource(id = R.drawable.back_navigation_icon),
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = ::backNavigation
                ))

        VerticalSpacer(10.dp)

        Row(Modifier.fillMaxWidth()) {

            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f)
                    .padding(4.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                AsyncImage(
                    model = posterImageUrl,
                    contentDescription = args.title,
                    contentScale = ContentScale.Crop)
            }

            Column(
                Modifier
                    .weight(1f, true)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start) {

                TextTitle(
                    title = args.title ?: stringResource(id = R.string.no_name),
                    color = Color.White, size = 24.sp
                )

                VerticalSpacer()

                TextContent(
                    title = String.format(stringResource(id = R.string.movie_rating), movieRating),
                    color = Color.White,
                    size = 16.sp
                )

                VerticalSpacer()

                TextContent(
                    title = String.format(stringResource(id = R.string.movie_release_date), args.releaseDate),
                    color = Color.White,
                    size = 16.sp
                )

                VerticalSpacer()

                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom) {

                    RatingBarView(
                        starsModifier = Modifier.size(20.dp),
                        rating = (args.voteAverage?.toDouble() ?: 0.0) / 2
                    )

                    HorizontalSpacer(5.dp)

                    TextContent(
                        title = args.voteAverage.toString().take(3),
                        color = Color.White,
                        size = 16.sp
                    )
                }

                VerticalSpacer()

                TextContent(
                    title = String.format(stringResource(id = R.string.movie_votes), args.voteCount),
                    color = Color.White,
                    size = 16.sp
                )

            }

        }

        VerticalSpacer()


        TextTitle(
            title = stringResource(id = R.string.overview_label),
            color = Color.White, size = 24.sp
        )

        VerticalSpacer()

        TextContent(
            title = args.overview ?: stringResource(id = R.string.no_overview),
            color = Color.White,
            size = 16.sp
        )

    }
}