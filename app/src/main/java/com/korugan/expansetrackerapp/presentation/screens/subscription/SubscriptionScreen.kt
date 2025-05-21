package com.korugan.expansetrackerapp.presentation.screens.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import blue
import com.korugan.expansetrackerapp.R
import com.korugan.expansetrackerapp.presentation.common.components.BottomNavigationBar

@Composable
fun SubscriptionScreen(
    navController: NavHostController,
    viewModel: SubscriptionViewModel = hiltViewModel()
) {
    val subs = viewModel.allSubs.collectAsState()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            "",
                            tint = blue,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Text("Your Subscriptions", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }
                IconButton(onClick = { navController.navigate("addSubscription") }) {
                    Icon(Icons.Default.Add, "", tint = blue, modifier = Modifier.size(30.dp))
                }
            }
            Spacer(Modifier.padding(5.dp))
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        LazyColumn(
            Modifier
                .padding(it)
                .padding(10.dp)
        ) {
            items(subs.value.size) {
                val sub = subs.value[it]
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .height(80.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Image(
                            if (sub.amount > 0) painterResource(R.drawable.ic_transaction) else painterResource(
                                R.drawable.ic_transaction_negative
                            ),
                            "",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                        )
                        Spacer(Modifier.padding(5.dp))
                        Column {
                            Text(sub.title, fontSize = 18.sp, fontWeight = FontWeight.W500)
                            Text("Renews monthly on the "+sub.dayOfMonth, color = Color.Gray)
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "$${sub.amount}",
                            color = if (sub.amount > 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                            fontWeight = FontWeight.W600
                        )
                        IconButton(
                            onClick = {
                                viewModel.deleteSubscription(sub.id)
                            },
                            modifier = Modifier.size(30.dp)) {
                            Icon(
                                Icons.Default.Delete,
                                "",
                                tint = MaterialTheme.colorScheme.inverseSurface
                            )
                        }
                    }
                }
                Spacer(Modifier.padding(5.dp))
            }
        }
    }
}