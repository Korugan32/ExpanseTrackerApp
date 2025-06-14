package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blue

@Composable
fun AddingComponent(
    text: String,
    iconResource: Int,
    isSubscription: Boolean = false,
    onClick: () -> Unit = {},
) {
    Row(
        Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable { onClick() }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isSubscription) Icon(
                painterResource(iconResource),
                "",
                modifier = Modifier.size(20.dp)
            ) else Image(painterResource(iconResource), "", modifier = Modifier.size(20.dp))
            Spacer(Modifier.padding(2.dp))
            Text(text, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp)
        }
        Icon(Icons.Default.KeyboardArrowRight, "", tint = blue)
    }
    Spacer(Modifier.padding(5.dp))
}