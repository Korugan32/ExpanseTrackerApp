package com.korugan.expansetrackerapp.data.remote.crypto.historical.dto

data class CryptoSymbolHistoricalDtoItem(
    val market_cap: Long,
    val price: Double,
    val timestamp: String,
    val volume_24h: Long
)