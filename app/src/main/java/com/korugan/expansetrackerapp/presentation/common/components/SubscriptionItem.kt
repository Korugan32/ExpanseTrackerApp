package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korugan.expansetrackerapp.R
import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription

@Composable
fun SubscriptionItem(sub: Subscription, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ),
            leadingContent = {
                Image(
                    painter = if (sub.amount > 0)
                        painterResource(R.drawable.ic_transaction)
                    else
                        painterResource(R.drawable.ic_transaction_negative),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            },
            headlineContent = {
                Text(
                    text = sub.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            },
            supportingContent = {
                Text(
                    text = "Renews monthly on the ${sub.dayOfMonth}",
                    color = Color.Gray
                )
            },
            trailingContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${sub.amount}",
                        fontSize = 15.sp,
                        color = if (sub.amount > 0)
                            MaterialTheme.colorScheme.surface
                        else
                            MaterialTheme.colorScheme.inverseSurface,
                        fontWeight = FontWeight.W600
                    )
                    IconButton(
                        onClick = { onClick() },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                }
            }
        )
    }
}