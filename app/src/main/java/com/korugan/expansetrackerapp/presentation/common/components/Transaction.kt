package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korugan.expansetrackerapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Transaction(
    text: String,
    value: Double,
    description: String,
    date: Date,
    onclick: () -> Unit = {}
) {
    val formatter = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
    val formattedDate = formatter.format(date)
    var expanded by remember { mutableStateOf(false) }
    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 120.dp else 70.dp,
        label = "ExpandAnimation"
    )
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "RotationAnimation"
    )
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
    ) {
        Row(
            Modifier.fillMaxWidth().clickable { expanded = !expanded }.padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Image(
                    if (value > 0) painterResource(R.drawable.ic_transaction) else painterResource(R.drawable.ic_transaction_negative),
                    "",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(30.dp)
                )
                Spacer(Modifier.padding(5.dp))
                Column {
                    Text(text, fontSize = 18.sp, fontWeight = FontWeight.W500)
                    Text(formattedDate, color = Color.Gray)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "$${value}",
                    color = if (value > 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.W600
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand Arrow",
                    modifier = Modifier
                        .size(14.dp)
                        .rotate(rotation),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        if (expanded) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Description : $description",
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    onClick = onclick,
                    content = {
                        Icon(
                            Icons.Default.Delete, contentDescription = "DeleteIcon",
                            tint = MaterialTheme.colorScheme.inverseSurface,
                        )
                    }
                )
            }
        }
    }
    Spacer(Modifier.padding(5.dp))
}