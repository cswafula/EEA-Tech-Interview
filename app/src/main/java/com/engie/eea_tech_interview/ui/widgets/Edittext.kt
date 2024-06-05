package com.engie.eea_tech_interview.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engie.eea_tech_interview.ui.theme.Grey
import com.engie.eea_tech_interview.ui.theme.greyBackground

@Composable
fun OutlinedSearchEditText(
    label: String,
    searchString: String,
    onStringChange: (value: String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
    )
) {
    val focusManager = LocalFocusManager.current

    val grey = Grey
    val lightGrey = greyBackground

    OutlinedTextField(
        value = searchString,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search Icon"
            )
        },
        onValueChange = {
            onStringChange(it)
        },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .testTag(label)
            .height(57.dp)
            .fillMaxWidth()
            .onFocusChanged {}
            .testTag("Search Field"),
        keyboardOptions = keyboardOptions,
        label = {
            TextContent(
                title = label,
                weight = FontWeight.Normal,
                size = 16.sp
            )

        },
        shape = RoundedCornerShape(10.dp),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = grey,
            unfocusedBorderColor = lightGrey,
            disabledBorderColor = lightGrey,
            focusedLabelColor = grey,
        ),
        singleLine = true
    )
}