package com.korugan.expansetrackerapp.data.remote.stock.graph.dto

data class StockSymbolGraphDto(
    val historical: List<Historical>,
    val symbol: String
)