package com.korugan.expansetrackerapp.data.remote.crypto.ticker.dto

data class CryptoTickerDto(
    val beta_value: Double,
    val first_data_at: String,
    val id: String,
    val last_updated: String,
    val max_supply: Int,
    val name: String,
    val quotes: Quotes,
    val rank: Int,
    val symbol: String,
    val total_supply: Int
)