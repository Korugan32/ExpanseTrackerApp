package com.korugan.expansetrackerapp.presentation.screens.stock

import com.korugan.expansetrackerapp.data.remote.stock.symbol.dto.StockSymbolDto

data class StockSymbolsState(
    val isLoading: Boolean = false,
    val stockSymbols: StockSymbolDto? = null,
    val error: String = ""
)
