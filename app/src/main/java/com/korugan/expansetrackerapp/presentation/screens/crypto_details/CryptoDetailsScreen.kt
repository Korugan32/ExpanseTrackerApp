package com.korugan.expansetrackerapp.presentation.screens.crypto_details

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import blue
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.presentation.SharedViewModel
import com.korugan.expansetrackerapp.presentation.common.components.Header
import com.korugan.expansetrackerapp.presentation.common.components.TextFieldItem
import holdColor
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.Line
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DefaultLocale")
@Composable
fun CryptoDetailsScreen(
    navController: NavHostController,
    id: String,
    viewModel: CryptoDetailsViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    symbol: String,
) {
    val context = LocalContext.current
    var interval by remember { mutableStateOf("7d") }
    val cryptoTickerState = viewModel.cryptoTickerState.collectAsState()
    val cryptoSymbolHistoryState = viewModel.cryptoSymbolHistoryState.collectAsState()
    var isDatePickerEnable by remember { mutableStateOf(false) }
    var isDescriptionEnable by remember { mutableStateOf(false) }
    var assetName by remember { mutableStateOf("") }
    var assetDescription by remember { mutableStateOf("") }
    var assetAmount by remember { mutableStateOf("") }
    var assetPrice by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(Date()) }
    val dataPickerState = rememberDatePickerState()

    LaunchedEffect(Unit) {
        viewModel.getCryptoTicker(id)
        viewModel.getCryptoSymbolHistory(id)
    }

    Scaffold(
        topBar = { Header(navController, symbol) },
        modifier = Modifier.statusBarsPadding()
    ) {
        LazyColumn(
            Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if ((cryptoTickerState.value.error.isNotEmpty() || cryptoSymbolHistoryState.value.error.isNotEmpty()) && cryptoTickerState.value.cryptoTickers.isEmpty() || cryptoSymbolHistoryState.value.cryptoSymbolHistory == null) {
                item {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(cryptoTickerState.value.error)
                        Log.e("Error", cryptoTickerState.value.error)
                    }
                }
            }
            if (cryptoTickerState.value.isLoading || cryptoSymbolHistoryState.value.isLoading) {
                item {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            if (cryptoTickerState.value.cryptoTickers[id] != null && cryptoSymbolHistoryState.value.cryptoSymbolHistory != null) {
                val ticker = cryptoTickerState.value.cryptoTickers[id]
                val prices = cryptoSymbolHistoryState.value.cryptoSymbolHistory!!.map { it.price }
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            "$" + String.format(
                                "%.2f",
                                ticker?.quotes?.USD?.price
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.W900,
                            fontSize = 25.sp
                        )
                        Spacer(Modifier.padding(5.dp))
                        Text(
                            "(" + String.format(
                                "%.2f",
                                ticker?.quotes?.USD?.percent_change_24h
                            ) + "%)",
                            color = if (ticker!!.quotes.USD.percent_change_24h > 0.0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(Modifier.padding(5.dp))
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(horizontal = 15.dp),
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
                        data = remember {
                            listOf(
                                Line(
                                    label = "$symbol $interval Graph",
                                    values = prices,
                                    color = SolidColor(holdColor),
                                    firstGradientFillColor = holdColor.copy(alpha = 1f),
                                    secondGradientFillColor = Color.Transparent,
                                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                                    gradientAnimationDelay = 1000,
                                    drawStyle = DrawStyle.Stroke(width = 2.dp),
                                ),
                            )
                        },
                        animationMode = AnimationMode.Together(delayBuilder = {
                            it * 500L
                        }),
                    )
                    Spacer(Modifier.padding(5.dp))
                    Text("Period(Interval)")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                interval = "7d"; viewModel.getCryptoSymbolHistory(
                                id,
                                "7d"
                            )
                            },
                            shape = RoundedCornerShape(8.dp), // Köşeleri yuvarlatılmış buton
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            modifier = Modifier.weight(1f) // Eşit genişlikte butonlar
                        ) {
                            Text("7 Day")
                        }

                        Button(
                            onClick = {
                                interval = "30d"; viewModel.getCryptoSymbolHistory(
                                id,
                                "30d"
                            )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("30 Day")
                        }

                        Button(
                            onClick = {
                                interval = "1y"; viewModel.getCryptoSymbolHistory(
                                id,
                                "1y"
                            )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("1 Year")
                        }
                    }
                }
                item {
                    if (cryptoTickerState.value.cryptoTickers[id] != null) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Create Asset",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.W600,
                                fontSize = 25.sp
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextFieldItem(
                                title = "Name",
                                placeHolder = {
                                    Text(
                                        symbol,
                                        textAlign = TextAlign.End,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                assetName,
                                onValueChange = { assetName = it },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text
                                )
                            )
                            TextFieldItem(
                                title = "Amount",
                                placeHolder = {
                                    Text(
                                        "0",
                                        textAlign = TextAlign.End,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                assetAmount,
                                onValueChange = { assetAmount = it },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            )
                            TextFieldItem(
                                title = "Purchase Price",
                                placeHolder = {
                                    Text(
                                        "$" + String.format(
                                            "%.2f",
                                            ticker!!.quotes.USD.price
                                        ),
                                        textAlign = TextAlign.End,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                assetPrice,
                                onValueChange = { assetPrice = it },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Icon(imageVector = Icons.Default.DateRange, "", tint = blue)
                                    Spacer(Modifier.padding(5.dp))
                                    Text(
                                        "Date",
                                        color = MaterialTheme.colorScheme.onBackground,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 20.sp,
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    val date = date
                                    val formatter = SimpleDateFormat(
                                        "dd.MM.yyyy",
                                        Locale.getDefault()
                                    )
                                    val formattedDate = formatter.format(date)
                                    Text(formattedDate.toString())
                                    IconButton({ isDatePickerEnable = !isDatePickerEnable }) {
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            "",
                                            tint = blue
                                        )
                                    }
                                }
                            }
                            Spacer(Modifier.padding(5.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Icon(imageVector = Icons.Default.Info, "", tint = blue)
                                    Spacer(Modifier.padding(5.dp))
                                    Text(
                                        "Description",
                                        color = MaterialTheme.colorScheme.onBackground,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 20.sp,
                                    )
                                }
                                IconButton({ isDescriptionEnable = !isDescriptionEnable }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        "",
                                        tint = blue
                                    )
                                }
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = {
                                        if (assetAmount.isNotEmpty()) {
                                            if (assetAmount.toDouble() > 0) {
                                                sharedViewModel.insertAsset(
                                                    Assets(
                                                        category = 0,
                                                        name = if (assetName.isEmpty()) symbol else assetName,
                                                        description = assetDescription,
                                                        amount = assetAmount.toDouble(),
                                                        purchasePrice = if (assetPrice.isEmpty()) ticker!!.quotes.USD.price else assetPrice.toDouble(),
                                                        symbol = ticker!!.id,
                                                        createDate = date
                                                    )
                                                )
                                                Toast.makeText(
                                                    context,
                                                    "Asset Successfully Added",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                navController.navigate("home")
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Amount must be greater than 0",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Amount cannot be empty",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    },
                                    modifier = Modifier.width(300.dp)
                                ) {
                                    Text("Add")
                                }
                            }
                            if (isDatePickerEnable) {
                                val selectedDateMillis = dataPickerState.selectedDateMillis
                                date = selectedDateMillis?.let { Date(it) } ?: Date()
                                ModalBottomSheet(onDismissRequest = {
                                    isDatePickerEnable = !isDatePickerEnable
                                }, containerColor = MaterialTheme.colorScheme.background) {
                                    DatePicker(
                                        colors = DatePickerDefaults.colors(
                                            containerColor = MaterialTheme.colorScheme.background,
                                            dayContentColor = MaterialTheme.colorScheme.onBackground
                                        ),
                                        state = dataPickerState
                                    )
                                }
                            }
                            if (isDescriptionEnable) {
                                ModalBottomSheet(
                                    onDismissRequest = {
                                        isDescriptionEnable = !isDescriptionEnable
                                    },
                                    containerColor = MaterialTheme.colorScheme.background
                                ) {
                                    Column(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(15.dp)
                                    ) {
                                        Text(
                                            "Add Description",
                                            fontWeight = FontWeight.W600,
                                            fontSize = 20.sp
                                        )
                                        Spacer(Modifier.padding(5.dp))
                                        TextField(
                                            assetDescription,
                                            onValueChange = { assetDescription = it },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(300.dp)
                                                .clip(
                                                    RoundedCornerShape(15.dp),
                                                ),
                                            textStyle = TextStyle(
                                                fontWeight = FontWeight.W600,
                                                fontSize = 20.sp
                                            ),
                                            colors = TextFieldDefaults.colors(
                                                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent
                                            ),
                                            placeholder = {
                                                Text(
                                                    "Description",
                                                    fontWeight = FontWeight.W500,
                                                    fontSize = 20.sp,
                                                    modifier = Modifier.fillMaxWidth()
                                                )
                                            },
                                        )
                                        Row(
                                            Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            TextButton({
                                                isDescriptionEnable =
                                                    !isDescriptionEnable; assetDescription = ""
                                            }) {
                                                Text(
                                                    "Cancel",
                                                    color = blue,
                                                    fontWeight = FontWeight.W600,
                                                    fontSize = 18.sp
                                                )
                                            }
                                            TextButton({
                                                isDescriptionEnable = !isDescriptionEnable
                                            }) {
                                                Text(
                                                    "Add",
                                                    color = blue,
                                                    fontWeight = FontWeight.W600,
                                                    fontSize = 18.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
