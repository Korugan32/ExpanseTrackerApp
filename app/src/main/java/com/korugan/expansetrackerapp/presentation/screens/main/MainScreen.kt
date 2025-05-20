package com.korugan.expansetrackerapp.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import blue
import com.korugan.expansetrackerapp.presentation.SharedViewModel
import com.korugan.expansetrackerapp.presentation.common.components.AssetComponent
import com.korugan.expansetrackerapp.presentation.common.components.BottomNavigationBar
import com.korugan.expansetrackerapp.presentation.common.components.IncomeExpense
import com.korugan.expansetrackerapp.presentation.common.components.Transaction
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.Line

@SuppressLint("DefaultLocale")
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val allFinancial = viewModel.allFinancial.collectAsState()
    val allPositiveFinancial = sharedViewModel.allPositiveFinancial.collectAsState()
    val allNegativeFinancial = sharedViewModel.allNegativeFinancial.collectAsState()
    val allAssets = viewModel.allAssets.collectAsState()
    val crypto = sharedViewModel.cryptoTickerState.collectAsState()
    val stock = sharedViewModel.stockQuoteState.collectAsState()
    Scaffold(
        Modifier.navigationBarsPadding(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.background,
                onClick = { navController.navigate("financial") },
                shape = CircleShape,
                modifier = Modifier
                    .size(45.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Start,
        bottomBar = { BottomNavigationBar(navController) },
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            item {
                Row(
                    Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "HesapKitapp",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W600,
                        fontSize = 20.sp
                    )
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(
                            Icons.Default.Settings,
                            "",
                        )
                    }
                }
            }
            item {
                Column(Modifier.padding(horizontal = 15.dp)) {
                    Text("Total Balance")
                    Text(
                        String.format(
                            "$%.2f",
                            (allPositiveFinancial.value.sumOf { it.amount } + allNegativeFinancial.value.sumOf { it.amount })
                        ),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.padding(5.dp))
                /* Income and Expense Components */
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    if(allPositiveFinancial.value.sumOf { it.amount } != 0.0 || allNegativeFinancial.value.sumOf { it.amount } != 0.0){
                        IncomeExpense(allPositiveFinancial.value.sumOf { it.amount })
                        IncomeExpense(allNegativeFinancial.value.sumOf { it.amount })
                    }else{
                        Text("")
                    }

                }
            }
            item {
                Column(Modifier.padding(15.dp)) {
                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Transactions Chart", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    Spacer(Modifier.padding(5.dp))
                }
                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(horizontal = 22.dp),
                    indicatorProperties = HorizontalIndicatorProperties(
                        true,
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ),
                    labelHelperProperties = LabelHelperProperties(
                        true,
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ),
                    data = listOf(
                        Line(
                            label = "Incomes",
                            values = if (allPositiveFinancial.value.isNotEmpty()) allPositiveFinancial.value.sortedBy { it.createDate }
                                .map { it.amount } else listOf(
                                0.0,
                                0.0,
                                0.0,
                                0.0
                            ),
                            color = SolidColor(Color(0xFF14770C)),
                            firstGradientFillColor = Color(0xFF14770C).copy(alpha = 1f),
                            secondGradientFillColor = Color.Transparent,
                            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                            gradientAnimationDelay = 1000,
                            drawStyle = DrawStyle.Stroke(width = 2.dp),
                        ),
                        Line(
                            label = "Expenses",
                            values = if (allNegativeFinancial.value.isNotEmpty()) allNegativeFinancial.value.sortedBy { it.createDate }
                                .map { it.amount } else listOf(
                                0.0,
                                0.0,
                                0.0,
                                0.0
                            ),
                            color = SolidColor(Color(0xFFA10505)),
                            firstGradientFillColor = Color(0xFFA10505).copy(alpha = 1f),
                            secondGradientFillColor = Color.Transparent,
                            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                            gradientAnimationDelay = 1000,
                            drawStyle = DrawStyle.Stroke(width = 2.dp),
                        )
                    ),
                    animationMode = AnimationMode.Together(delayBuilder = {
                        it * 500L
                    }),
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
                        Text("Your Assets", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    if (allAssets.value.isEmpty()) {
                        Spacer(Modifier.height(15.dp))
                        Column(
                            Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Assets Not Found")
                        }
                    }
                }
            }
            items(allAssets.value.size) { asset ->
                Column(modifier = Modifier.padding(5.dp)) {
                    val cryptoTicker = crypto.value.cryptoTickers[allAssets.value[asset].symbol]

                    if (allAssets.value[asset].category == 0) {
                        if (cryptoTicker == null) {
                            LaunchedEffect(allAssets.value[asset].symbol) {
                                sharedViewModel.getCryptoTicker(allAssets.value[asset].symbol)
                            }
                        }
                        AssetComponent(
                            allAssets.value[asset],
                            cryptoTicker?.quotes?.USD?.price
                        ) { viewModel.deleteAsset(allAssets.value[asset].id) }
                    } else {
                        val stockQuote = stock.value.stockQuote[allAssets.value[asset].symbol]
                        if (stockQuote == null) {
                            LaunchedEffect(allAssets.value[asset].symbol) {
                                sharedViewModel.getStockQuote(allAssets.value[asset].symbol)
                            }
                        }
                        AssetComponent(
                            allAssets.value[asset],
                            stockQuote?.c
                        ) { viewModel.deleteAsset(allAssets.value[asset].id) }
                    }
                }

            }
            item {
                Column(Modifier.padding(15.dp)) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Transactions", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        IconButton({
                            navController.navigate("transactions")
                        }) {
                            Icon(Icons.Default.KeyboardArrowRight, "", tint = blue)
                        }
                    }
                    if (allFinancial.value.isEmpty()) {
                        Spacer(Modifier.height(15.dp))
                        Column(
                            Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Transactions Not Found")
                        }
                    }
                }
            }
            items(allFinancial.value.size) { index ->
                Column(modifier = Modifier.padding(5.dp)) {
                    Transaction(
                        allFinancial.value[index].name,
                        allFinancial.value[index].amount,
                        allFinancial.value[index].description,
                        allFinancial.value[index].createDate
                    ) { sharedViewModel.deleteFinancial(allFinancial.value[index].id) }
                }
            }
        }
    }
}
