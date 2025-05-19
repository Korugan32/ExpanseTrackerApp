package com.korugan.expansetrackerapp.presentation.screens.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.korugan.expansetrackerapp.presentation.common.components.GoalComponent

@Composable
fun GoalsScreen(navController: NavHostController, viewModel: GoalsViewModel = hiltViewModel()) {
    val goals = viewModel.allGoals.collectAsState()
    Scaffold(
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Your Goals", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                IconButton(onClick = { navController.navigate("addGoal") }) {
                    Icon(Icons.Default.Add, "", tint = blue, modifier = Modifier.size(30.dp))
                }
            }
            Spacer(Modifier.padding(5.dp))
        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.systemBarsPadding()
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
                .padding(horizontal = 15.dp)
        ) {
            items(goals.value.size) { index ->
                GoalComponent(goals.value[index],navController)
            }
        }
    }
}