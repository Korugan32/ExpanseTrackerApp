package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.korugan.expansetrackerapp.util.Constants.screenWidth

@Composable
fun IncomeExpense(value: Double) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier =  Modifier
            .shadow(10.dp, shape = RoundedCornerShape(15.dp))
            .height(80.dp)
            .width(screenWidth() * 0.45.dp)
            .background(MaterialTheme.colorScheme.onPrimary),
        overlineContent = {
            Text(
                if (value > 0) "Income" else "Expense",
                color = if (value > 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        },
        headlineContent = {
            Text("$${value}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        },
        leadingContent = {
            Icon(
                if (value > 0) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                "",
                tint = if (value > 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.size(40.dp)
            )
        },
    )
}