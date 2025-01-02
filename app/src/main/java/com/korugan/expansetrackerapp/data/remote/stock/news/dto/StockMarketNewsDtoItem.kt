package com.korugan.expansetrackerapp.data.remote.stock.news.dto

data class StockMarketNewsDtoItem(
    val category: String,
    val datetime: Int,
    val headline: String,
    val id: Int,
    val image: String,
    val related: String,
    val source: String,
    val summary: String,
    val url: String
)