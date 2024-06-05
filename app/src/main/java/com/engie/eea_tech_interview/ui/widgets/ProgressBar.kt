package com.engie.eea_tech_interview.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.engie.eea_tech_interview.R

@Composable
fun CustomLoader() {
    fun dismissLottieDialog() {}

    Dialog(onDismissRequest = ::dismissLottieDialog) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            LottieAnime(playing = true)
        }
    }
}

@Composable
fun LottieAnime(playing: Boolean) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loader)
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {

        LottieAnimation(
            composition = composition,
            modifier = Modifier.fillMaxSize(),
            iterations = LottieConstants.IterateForever,
            speed = 1f,
            isPlaying = playing,
            restartOnPlay = false
        )
    }

}