package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldItem(
    title: String,
    placeHolder: @Composable () -> Unit,
    text: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp
        )
        TextField(
            text, onValueChange = onValueChange,
            Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(15.dp),
                ),
            keyboardOptions = keyboardOptions,
            textStyle = TextStyle(
                textAlign = TextAlign.End,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp
            ),
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = placeHolder,
        )
    }
}