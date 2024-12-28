package com.korugan.expansetrackerapp.data.remote.stock.symbol.dto

data class StockSymbolDtoItem(
    val currency: String,
    val description: String,
    val displaySymbol: String,
    val figi: String,
    val isin: Any,
    val mic: String,
    val shareClassFIGI: String,
    val symbol: String,
    val symbol2: String,
    val type: String
)