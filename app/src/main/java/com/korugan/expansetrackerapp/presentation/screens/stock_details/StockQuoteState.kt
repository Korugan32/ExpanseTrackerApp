package com.korugan.expansetrackerapp.presentation.screens.stock_details

import com.korugan.expansetrackerapp.data.remote.stock.quote.dto.StockQuoteDto

data class StockQuoteState(
    val isLoading : Boolean = false,
    val stockQuote : Map<String, StockQuoteDto> = emptyMap(),
    val error : String = ""
)
