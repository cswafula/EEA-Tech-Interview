package com.engie.eea_tech_interview.ui.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(height: Dp = 12.dp) {
    Spacer(modifier = Modifier.height(height)
        .testTag("Vertical Spacer"))
}

@Composable
fun HorizontalSpacer(width: Dp = 12.dp) {
    Spacer(modifier = Modifier.width(width)
        .testTag("Horizontal Spacer"))
}