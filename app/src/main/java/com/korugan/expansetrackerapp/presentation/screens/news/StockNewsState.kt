package com.korugan.expansetrackerapp.presentation.screens.news

import com.korugan.expansetrackerapp.data.remote.stock.news.dto.StockMarketNewsDto

data class StockNewsState(
    val isLoading: Boolean = false,
    val stockNews: StockMarketNewsDto? = null,
    val error: String = ""
)
