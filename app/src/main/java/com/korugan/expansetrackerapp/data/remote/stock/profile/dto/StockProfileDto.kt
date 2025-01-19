package com.korugan.expansetrackerapp.data.remote.stock.profile.dto

data class StockProfileDto(
    val country: String? = null,
    val currency: String? = null,
    val estimateCurrency: String? = null,
    val exchange: String? = null,
    val finnhubIndustry: String? = null,
    val ipo: String? = null,
    val logo: String? = null,
    val marketCapitalization: Double? = null,
    val name: String? = null,
    val phone: String? = null,
    val shareOutstanding: Double? = null,
    val ticker: String? = null,
    val weburl: String? = null,
)