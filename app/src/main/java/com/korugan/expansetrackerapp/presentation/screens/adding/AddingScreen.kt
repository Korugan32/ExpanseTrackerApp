package com.korugan.expansetrackerapp.presentation.screens.adding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import blue
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
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .clickable { navController.navigate("addSubscription") }
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painterResource(R.drawable.ic_subscription),
                                "",
                                modifier = Modifier.size(20.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(Modifier.padding(2.dp))
                            Text(
                                "Subscription",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 18.sp
                            )
                        }
                        Icon(Icons.Default.KeyboardArrowRight, "", tint = blue)
                    }
                    Spacer(Modifier.padding(5.dp))
                }
            }
        }
    }
}
