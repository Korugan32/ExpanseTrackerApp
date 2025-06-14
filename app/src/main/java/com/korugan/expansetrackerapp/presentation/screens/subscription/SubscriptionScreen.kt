package com.korugan.expansetrackerapp.presentation.screens.subscription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import blue
import com.korugan.expansetrackerapp.presentation.common.components.BottomNavigationBar
import com.korugan.expansetrackerapp.presentation.common.components.SubscriptionItem

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
                SubscriptionItem(sub){
                    viewModel.deleteSubscription(sub.id)
                }
            }
        }
    }
}