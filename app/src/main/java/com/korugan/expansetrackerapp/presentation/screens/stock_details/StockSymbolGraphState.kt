package com.korugan.expansetrackerapp.presentation.screens.stock_details

import com.korugan.expansetrackerapp.data.remote.stock.graph.dto.StockSymbolGraphDto

data class StockSymbolGraphState(
    val isLoading: Boolean = false,
    val stockSymbolGraph: StockSymbolGraphDto? = null,
    val error: String = ""
)

