package com.korugan.expansetrackerapp.presentation.screens.transactions

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import blue
import com.korugan.expansetrackerapp.presentation.SharedViewModel
import com.korugan.expansetrackerapp.presentation.common.components.BottomNavigationBar
import com.korugan.expansetrackerapp.presentation.common.components.Transaction

@Composable
fun TransactionScreen(
    navController: NavHostController,
    viewModel: SharedViewModel = hiltViewModel()
) {
    val negativeFinancial = viewModel.allNegativeFinancial.collectAsState()
    val positiveFinancial = viewModel.allPositiveFinancial.collectAsState()
    Scaffold(
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Your Transactions", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                IconButton(onClick = { navController.navigate("addTransactions") }) {
                    Icon(Icons.Default.Add, "", tint = blue, modifier = Modifier.size(30.dp))
                }
            }
            Spacer(Modifier.padding(5.dp))
        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.systemBarsPadding()
    ) {
        LazyColumn(Modifier.padding(it)) {
            item {
                Column(Modifier.padding(15.dp)) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Positive Financial", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    if (positiveFinancial.value.isEmpty()) {
                        Spacer(Modifier.height(15.dp))
                        Column(
                            Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Positive Financial Not Found")
                        }
                    }
                }
            }
            items(positiveFinancial.value.size) { index ->
                Transaction(
                    positiveFinancial.value[index].name,
                    positiveFinancial.value[index].amount,
                    positiveFinancial.value[index].description,
                    positiveFinancial.value[index].createDate
                )
            }
            item {
                Column(Modifier.padding(15.dp)) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Negative Financial", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    if (negativeFinancial.value.isEmpty()) {
                        Spacer(Modifier.height(15.dp))
                        Column(
                            Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Negative Financial Not Found")
                        }
                    }
                }
            }
            items(negativeFinancial.value.size) { index ->
                Transaction(
                    negativeFinancial.value[index].name,
                    negativeFinancial.value[index].amount,
                    negativeFinancial.value[index].description,
                    negativeFinancial.value[index].createDate
                )
            }
        }
    }
}