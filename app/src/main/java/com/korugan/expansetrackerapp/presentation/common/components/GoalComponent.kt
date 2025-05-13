package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun GoalComponent(goals: Goals,navController: NavController) {
    Row(
        Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable { navController.navigate("goalDetails/${goals.id}") },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.1f)
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp)),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.goal),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(50.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = goals.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            LinearProgressIndicator(
                progress = (goals.progress/100).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(8.dp)),
                trackColor = Color.Gray.copy(0.4f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Saved $${String.format("%.1f",goals.totalInsert)} / %" + String.format("%.1f", abs(goals.progress)),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = blue
                )
                Text(
                    text = "Total : $${goals.price}",
                    fontSize = 15.sp
                )
            }
        }
    }
    Spacer(Modifier.padding(5.dp))
}