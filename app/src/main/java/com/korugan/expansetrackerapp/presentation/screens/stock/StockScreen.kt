package com.korugan.expansetrackerapp.presentation.screens.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import blue
import com.korugan.expansetrackerapp.presentation.common.components.StockItem

@Composable
fun StockScreen(navController: NavHostController, viewModel: StockViewModel = hiltViewModel()) {
    var searchText by remember { mutableStateOf("") }
    var isSearch by remember { mutableStateOf(false) }
    val stockState by viewModel.stockState.collectAsState()
    val stockSearchState by viewModel.stockSearchState.collectAsState()
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
        topBar = {
            Column {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            "",
                            tint = blue,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    TextField(
                        searchText, onValueChange = { searchText = it },
                        Modifier
                            .clip(
                                RoundedCornerShape(20.dp),
                            ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text("Search") },
                    )
                    IconButton(onClick = {
                        isSearch = true
                        if (searchText.isNotBlank()) {
                            viewModel.getStockSearchSymbols(searchText)
                        }
                    }) {
                        Icon(
                            Icons.Default.Search,
                            "",
                            tint = blue,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Spacer(Modifier.padding(2.dp))
            }
        },
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            if (!isSearch) {
                if (stockState.isLoading) {
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
                if (stockState.error.isNotEmpty()) {
                    item {
                        Text(text = stockState.error)
                    }
                }
                if (stockState.stockSymbols != null) {
                    items(stockState.stockSymbols!!.size) { index ->
                        StockItem(
                            name = stockState.stockSymbols!![index].description,
                            symbol = stockState.stockSymbols!![index].symbol,
                            navController = navController,
                        )
                    }
                }
            } else {
                if (stockSearchState.isLoading) {
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
                if (stockSearchState.error.isNotEmpty()) {
                    item {
                        Text(text = stockSearchState.error)
                    }
                }
                if (stockSearchState.stockSymbols != null) {
                    items(stockSearchState.stockSymbols!!.result.size) { index ->
                        StockItem(
                            name = stockSearchState.stockSymbols!!.result[index].description,
                            symbol = stockSearchState.stockSymbols!!.result[index].symbol,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}