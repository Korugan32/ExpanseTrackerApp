package com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.dto

data class Result(
    val description: String,
    val displaySymbol: String,
    val symbol: String,
    val type: String
)