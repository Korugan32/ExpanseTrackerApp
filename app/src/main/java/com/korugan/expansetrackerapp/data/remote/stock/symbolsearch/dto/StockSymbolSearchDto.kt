package com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.dto

data class StockSymbolSearchDto(
    val count: Int,
    val result: List<Result>
)