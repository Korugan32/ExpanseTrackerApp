package com.korugan.expansetrackerapp.presentation.common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import blue

@SuppressLint("DefaultLocale")
@Composable
fun CryptoItem(
    navController: NavHostController,
    symbol: String,
    name: String,
    price: Double,
    percentage: Double,
    id: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable {
                navController.navigate("cryptoDetails" + "?id=${id}&symbol=${symbol}")
            }
            .padding(vertical = 8.dp)
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(symbol, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp)
            Spacer(Modifier.padding(2.dp))
            Text(name, color = Color.Gray, fontSize = 18.sp)
            Spacer(Modifier.padding(2.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Text(
                    "$" + String.format("%.2f", price),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    "%" + String.format("%.2f", percentage),
                    color = if (percentage > 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                )
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "", tint = blue)
        }
    }
    HorizontalDivider()
}

@Composable
fun CryptoItem(navController: NavHostController, symbol: String, name: String, id: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable {
                navController.navigate("cryptoDetails" + "?id=${id}&symbol=${symbol}")
            }
            .padding(vertical = 8.dp)
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(symbol, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp)
            Spacer(Modifier.padding(2.dp))
            Text(name, color = Color.Gray, fontSize = 18.sp)
            Spacer(Modifier.padding(2.dp))
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "", tint = blue)
    }
    HorizontalDivider()
}
