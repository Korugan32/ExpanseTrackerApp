package com.korugan.expansetrackerapp.presentation.screens.details

import com.korugan.expansetrackerapp.data.remote.stock.quote.dto.StockQuoteDto

data class StockQuoteState(
    val isLoading : Boolean = false,
    val stockQuote : StockQuoteDto? = null,
    val error : String = ""
)
