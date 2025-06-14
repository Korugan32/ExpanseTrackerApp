package com.korugan.expansetrackerapp.presentation.common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import blue
import com.korugan.expansetrackerapp.R
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import kotlin.math.abs

@SuppressLint("DefaultLocale")
@Composable
fun GoalComponent(goals: Goals, navController: NavController) {
    ListItem(
        headlineContent = {
            LinearProgressIndicator(
                progress = (goals.progress / 100).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(8.dp)),
                backgroundColor = Color.Gray.copy(0.4f),
                color = MaterialTheme.colorScheme.primary
            )
        },
        modifier = Modifier
            .shadow(8.dp, shape = RoundedCornerShape(15.dp))
            .clickable { navController.navigate("goalDetails/${goals.id}") },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        overlineContent = {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = goals.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        supportingContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Saved $${
                        String.format(
                            "%.1f",
                            goals.totalInsert
                        )
                    } / %" + String.format(
                        "%.1f",
                        abs(goals.progress)
                    ),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = blue
                )
                Text(
                    text = "Total : $${goals.price}",
                    fontSize = 15.sp
                )
            }
        },
        leadingContent = {
            Icon(
                painter = painterResource(id = R.drawable.goal),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(59.dp)
            )
        },
    )
    Spacer(Modifier.padding(5.dp))
}