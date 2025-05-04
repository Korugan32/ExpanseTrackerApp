package com.korugan.expansetrackerapp.presentation.screens.stock_details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
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
import buyColor
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.presentation.SharedViewModel
import com.korugan.expansetrackerapp.presentation.common.components.Header
import com.korugan.expansetrackerapp.presentation.common.components.TextFieldItem
import holdColor
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import sellColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DefaultLocale")
@Composable
fun StockDetailsScreen(
    navController: NavHostController,
    viewModel: StockDetailsViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    symbol: String?
) {
    val context = LocalContext.current
    val stockSymbolGraphState = viewModel.stockSymbolGraphState.collectAsState()
    val stockSymbolProfileState = viewModel.stockProfileState.collectAsState()
    val stockQuoteState = viewModel.stockQuoteState.collectAsState()
    val stockRecommendationState = viewModel.stockRecommendationState.collectAsState()
    var isDatePickerEnable by remember { mutableStateOf(false) }
    var isDescriptionEnable by remember { mutableStateOf(false) }
    var assetName by remember { mutableStateOf("") }
    var assetDescription by remember { mutableStateOf("") }
    var assetAmount by remember { mutableStateOf("") }
    var assetPrice by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(Date()) }
    val dataPickerState = rememberDatePickerState()

    Scaffold(
        topBar = { Header(navController, symbol!!) },
        modifier = Modifier.systemBarsPadding()
    ) {
        LazyColumn(Modifier.padding(it)) {
            item {
                if (stockSymbolProfileState.value.isLoading || stockQuoteState.value.isLoading) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { CircularProgressIndicator() }
                }
            }
            item {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .statusBarsPadding()
                ) {
                    if (stockSymbolGraphState.value.stockSymbolGraph != null && stockQuoteState.value.stockQuote[symbol] != null) {
                        val closePrices =
                            stockSymbolGraphState.value.stockSymbolGraph!!.historical.map { it.close }
                        val stockQuote = stockQuoteState.value.stockQuote[symbol]!!
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "$" + stockQuote.c,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.W900,
                                fontSize = 25.sp
                            )
                            Spacer(Modifier.padding(5.dp))
                            Text(
                                "" + String.format("%.2f", stockQuote.d),
                                color = if (stockQuote.d > 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                                fontWeight = FontWeight.W600,
                                fontSize = 20.sp
                            )
                            Spacer(Modifier.padding(5.dp))
                            Text(
                                "(" + String.format("%.2f", stockQuote.dp) + "%)",
                                color = if (stockQuote.dp > 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                                fontWeight = FontWeight.W600,
                                fontSize = 20.sp
                            )
                        }
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
                                        label = symbol!!,
                                        values = closePrices,
                                        color = SolidColor(blue),
                                        firstGradientFillColor = blue.copy(alpha = 1f),
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
                    }
                    if (stockSymbolProfileState.value.stockProfile != null) {
                        val stock = stockSymbolProfileState.value.stockProfile!!
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Spacer(Modifier.padding(5.dp))
                            Text(
                                "" + stock.name,
                                color = blue,
                                fontWeight = FontWeight.W600,
                                fontSize = 20.sp,
                                modifier = Modifier.clickable {
                                    val url = stock.weburl
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }
            }
            item {
                Spacer(Modifier.padding(5.dp))
                if (stockRecommendationState.value.stockRecommendation != null) {
                    val stock = stockRecommendationState.value.stockRecommendation!!
                    val recommendationBuy =
                        stockRecommendationState.value.stockRecommendation!!.map { it.buy }
                    val recommendationSell =
                        stockRecommendationState.value.stockRecommendation!!.map { it.sell }
                    val recommendationHold =
                        stockRecommendationState.value.stockRecommendation!!.map { it.hold }
                    Row(Modifier.padding(5.dp)) {
                        Text(
                            "Recommendation",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.W600,
                            fontSize = 25.sp
                        )
                    }
                    Spacer(Modifier.padding(5.dp))
                    ColumnChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
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
                        labelProperties = LabelProperties(
                            true,
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        ),
                        data = remember {
                            listOf(
                                Bars(
                                    label = stock[0].period,
                                    values = listOf(
                                        Bars.Data(
                                            label = "Buy",
                                            value = recommendationBuy[0].toDouble(),
                                            color = SolidColor(buyColor)
                                        ),
                                        Bars.Data(
                                            label = "Hold",
                                            value = recommendationHold[0].toDouble(),
                                            color = SolidColor(holdColor)
                                        ),
                                        Bars.Data(
                                            label = "Sell",
                                            value = recommendationSell[0].toDouble(),
                                            color = SolidColor(sellColor)
                                        )
                                    ),
                                ),
                                Bars(
                                    label = stock[1].period,
                                    values = listOf(
                                        Bars.Data(
                                            label = "Buy",
                                            value = recommendationBuy[1].toDouble(),
                                            color = SolidColor(buyColor)
                                        ),
                                        Bars.Data(
                                            label = "Hold",
                                            value = recommendationHold[1].toDouble(),
                                            color = SolidColor(holdColor)
                                        ),
                                        Bars.Data(
                                            label = "Sell",
                                            value = recommendationSell[1].toDouble(),
                                            color = SolidColor(sellColor)
                                        )
                                    ),
                                ),
                                Bars(
                                    label = stock[2].period,
                                    values = listOf(
                                        Bars.Data(
                                            label = "Buy",
                                            value = recommendationBuy[2].toDouble(),
                                            color = SolidColor(buyColor)
                                        ),
                                        Bars.Data(
                                            label = "Hold",
                                            value = recommendationHold[2].toDouble(),
                                            color = SolidColor(holdColor)
                                        ),
                                        Bars.Data(
                                            label = "Sell",
                                            value = recommendationSell[2].toDouble(),
                                            color = SolidColor(sellColor)
                                        )
                                    ),
                                ),
                                Bars(
                                    label = stock[3].period,
                                    values = listOf(
                                        Bars.Data(
                                            label = "Buy",
                                            value = recommendationBuy[3].toDouble(),
                                            color = SolidColor(buyColor)
                                        ),
                                        Bars.Data(
                                            label = "Hold",
                                            value = recommendationHold[3].toDouble(),
                                            color = SolidColor(holdColor)
                                        ),
                                        Bars.Data(
                                            label = "Sell",
                                            value = recommendationSell[3].toDouble(),
                                            color = SolidColor(sellColor)
                                        )
                                    ),
                                )
                            )
                        },
                        barProperties = BarProperties(
                            spacing = 3.dp,
                        ),
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ),
                    )
                }
            }
            item {
                if (stockQuoteState.value.stockQuote[symbol] != null) {
                    Spacer(Modifier.padding(5.dp))
                    val stockQuote = stockQuoteState.value.stockQuote[symbol]!!
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
                    ) {
                        TextFieldItem(
                            title = "Name",
                            placeHolder = {
                                Text(
                                    symbol!!,
                                    textAlign = TextAlign.End,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            assetName,
                            onValueChange = { assetName = it },
                            KeyboardOptions(
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
                            KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        TextFieldItem(
                            title = "Purchase Price",
                            placeHolder = {
                                Text(
                                    "$" + stockQuote.c,
                                    textAlign = TextAlign.End,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            assetPrice,
                            onValueChange = { assetPrice = it },
                            KeyboardOptions(
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
                                    textAlign = TextAlign.Start
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
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Button(
                                onClick = {
                                    if (assetAmount.isNotEmpty()) {
                                        if (assetAmount.toDouble() > 0) {
                                            sharedViewModel.insertAsset(
                                                Assets(
                                                    category = 1,
                                                    name = if (assetName.isEmpty()) symbol!! else assetName,
                                                    description = assetDescription,
                                                    amount = assetAmount.toDouble(),
                                                    purchasePrice = if (assetPrice.isEmpty()) stockQuote.c else assetPrice.toDouble(),
                                                    symbol = symbol!!,
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
                                        assetDescription, onValueChange = { assetDescription = it },
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
                                        TextButton({ isDescriptionEnable = !isDescriptionEnable }) {
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