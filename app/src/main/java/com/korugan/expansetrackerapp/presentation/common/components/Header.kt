package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import blue


@Composable
fun Header(navController: NavHostController, headerText : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                "",
                tint = blue,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(headerText, color = MaterialTheme.colorScheme.onBackground, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }
}