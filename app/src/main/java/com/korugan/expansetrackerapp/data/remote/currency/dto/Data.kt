package com.korugan.expansetrackerapp.data.remote.currency.dto

data class Data(
    val currencySymbol: String,
    val id: String,
    val rateUsd: String,
    val symbol: String,
    val type: String
)