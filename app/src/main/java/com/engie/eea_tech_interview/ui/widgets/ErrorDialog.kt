package com.engie.eea_tech_interview.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.ui.theme.Primary

@Composable
fun ErrorHandleView(
    message: String,
    onDismiss: () -> Unit
) {
    Column {
        Dialog(onDismissRequest = {}) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ErrorLayout(message, errorLayoutClose = {
                    onDismiss()
                })
            }
        }
    }

}

@Composable
fun ErrorLayout(
    message: String,
    errorLayoutClose: () -> Unit
) {
    Column(
        modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        VerticalSpacer(32.dp)
        Image(
            modifier = Modifier
                .height(72.dp)
                .width(72.dp),
            painter = painterResource(id = R.drawable.error_img),
            contentDescription = stringResource(R.string.error_image)
        )
        VerticalSpacer()

        VerticalSpacer()
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextContent(
                title = message,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                size = 17.sp
            )
        }
        VerticalSpacer()

        VerticalSpacer()

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                ),
                modifier = Modifier
                    .width(140.dp)
                    .height(46.dp),
                onClick = {errorLayoutClose() }) {
                Text(text = stringResource(id = R.string.close).uppercase())
            }
        }
        VerticalSpacer(20.dp)

    }
}