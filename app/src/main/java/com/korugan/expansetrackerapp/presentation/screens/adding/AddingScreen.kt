package com.korugan.expansetrackerapp.presentation.screens.adding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.korugan.expansetrackerapp.R
import com.korugan.expansetrackerapp.presentation.common.components.AddingComponent
import com.korugan.expansetrackerapp.presentation.common.components.BottomNavigationBar
import com.korugan.expansetrackerapp.presentation.common.components.Header

@Composable
fun AddingScreen(navController: NavHostController) {
    Scaffold(
        Modifier.systemBarsPadding(),
        bottomBar = { BottomNavigationBar(navController) },
        topBar = { Header(navController, "Add") }
    ) {
        LazyColumn(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(it),
        ) {
            item {
                Column(Modifier.padding(horizontal = 10.dp)) {
                    Spacer(Modifier.padding(5.dp))
                    Text(
                        text = "Assets",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(Modifier.padding(5.dp))
                    AddingComponent(
                        "Stocks(USA)",
                        R.drawable.ic_stock
                    ) { navController.navigate("assets") }
                    AddingComponent(
                        "Crypto",
                        R.drawable.ic_crypto
                    ) { navController.navigate("crypto") }
                }
            }
            item {
                Column(Modifier.padding(horizontal = 10.dp)) {
                    Text("Financial", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(Modifier.padding(5.dp))
                    AddingComponent("Goal", R.drawable.ic_goal) {
                        navController.navigate("addGoal")
                    }
                    AddingComponent("Income/Expense", R.drawable.ic_transaction) {
                        navController.navigate("addTransactions")
                    }
                    AddingComponent("Subscription", R.drawable.ic_subscription, true) {
                        navController.navigate("addSubscription")
                    }
                }
            }
        }
    }
}
