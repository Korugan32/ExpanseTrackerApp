package com.korugan.expansetrackerapp.domain.model.crypto

data class Crypto(
    val changePercent24Hr: String,
    val id: String,
    val name: String,
    val priceUsd: String,
    val symbol: String,
)
