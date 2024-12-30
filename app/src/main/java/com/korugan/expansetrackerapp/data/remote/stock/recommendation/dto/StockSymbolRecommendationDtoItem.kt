package com.korugan.expansetrackerapp.data.remote.stock.recommendation.dto

data class StockSymbolRecommendationDtoItem(
    val buy: Int,
    val hold: Int,
    val period: String,
    val sell: Int,
    val strongBuy: Int,
    val strongSell: Int,
    val symbol: String
)