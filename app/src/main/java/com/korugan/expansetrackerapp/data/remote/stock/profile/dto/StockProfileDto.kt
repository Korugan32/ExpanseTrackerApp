package com.korugan.expansetrackerapp.data.remote.stock.profile.dto

data class StockProfileDto(
    val country: String,
    val currency: String,
    val estimateCurrency: String,
    val exchange: String,
    val finnhubIndustry: String,
    val ipo: String,
    val logo: String,
    val marketCapitalization: Double,
    val name: String,
    val phone: String,
    val shareOutstanding: Double,
    val ticker: String,
    val weburl: String
)