package com.engie.eea_tech_interview.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engie.eea_tech_interview.ui.theme.FuturaBook

@Composable
fun TextContent(
    title: String,
    size: TextUnit = 14.sp,
    weight: FontWeight = FontWeight.Normal,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp,
    color: Color = Color.Black,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        style = TextStyle(fontSize = size, fontWeight = weight, fontFamily = FuturaBook),
        text = title,
        color = color,
        textAlign = textAlign,
        maxLines=maxLines,
        modifier = modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
    )
}

@Composable
fun TextTitle(
    title: String,
    size: TextUnit = 30.sp,
    weight: FontWeight = FontWeight.Bold,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp,
    color: Color = Color.Black,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        style = TextStyle(fontSize = size, fontWeight = weight, fontFamily = FuturaBook),
        text = title,
        color = color,
        textAlign = textAlign,
        maxLines=maxLines,
        modifier = modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
    )
}