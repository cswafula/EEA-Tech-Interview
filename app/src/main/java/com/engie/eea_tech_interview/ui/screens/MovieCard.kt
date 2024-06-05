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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.network.NetworkUtils
import com.engie.eea_tech_interview.ui.theme.greyBackground
import com.engie.eea_tech_interview.ui.widgets.HorizontalSpacer
import com.engie.eea_tech_interview.ui.widgets.RatingBarView
import com.engie.eea_tech_interview.ui.widgets.TextContent
import com.engie.eea_tech_interview.ui.widgets.TextTitle
import com.engie.eea_tech_interview.ui.widgets.VerticalSpacer


@Composable
fun MovieCard(movie: Movie, onCardClicked: (movie: Movie) -> Unit) {

    val movieRating = if(movie.adult) "R" else "PG"
    val imageUrl = NetworkUtils.IMAGES_BASE_URL.plus(movie.posterPath)

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(4.dp)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onCardClicked(movie) }
            ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Box(
            modifier = Modifier.heightIn(min = 200.dp, max = 250.dp)
        ) {

            AsyncImage(
                model = imageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop)


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 350f
                        )
                    )
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {

                TextTitle(
                    title = movie.title ?: stringResource(id = R.string.no_name), color = Color.White, size = 18.sp
                )

                VerticalSpacer(5.dp)

                TextContent(
                    title = String.format(stringResource(id = R.string.movie_rating), movieRating),
                    color = Color.White,
                    size = 16.sp
                )
                VerticalSpacer(4.dp)

                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom) {

                    RatingBarView(
                        starsModifier = Modifier.size(14.dp),
                        rating = movie.voteAverage / 2
                    )

                    HorizontalSpacer(5.dp)

                    TextContent(
                        title = movie.voteAverage.toString().take(3),
                        color = Color.White,
                        size = 14.sp
                    )
                }
            }

        }


    }

}