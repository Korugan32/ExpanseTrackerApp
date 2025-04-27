package com.korugan.expansetrackerapp.presentation.screens.stock

import com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.dto.StockSymbolSearchDto

data class StockSearchState (
    val isLoading: Boolean = false,
    val stockSymbols: StockSymbolSearchDto? = null,
    val error: String = ""
)