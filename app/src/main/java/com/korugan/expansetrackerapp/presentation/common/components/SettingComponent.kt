package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import blue
import com.korugan.expansetrackerapp.util.Constants.screenHeight

@Composable
fun SettingComponent(text : String, event : () -> Unit = {}){
    Row(
        Modifier
            .fillMaxWidth()
            .height(screenHeight()*0.07.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text,color = MaterialTheme.colorScheme.onBackground)
        IconButton(onClick = event) {
            Icon(Icons.Default.KeyboardArrowRight, "", tint = blue)
        }
    }
    HorizontalDivider()
}