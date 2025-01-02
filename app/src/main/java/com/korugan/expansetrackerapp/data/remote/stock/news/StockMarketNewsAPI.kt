package com.korugan.expansetrackerapp.data.remote.stock.news

import com.korugan.expansetrackerapp.data.remote.stock.news.dto.StockMarketNewsDto
import com.korugan.expansetrackerapp.util.Constants.STOCK_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface StockMarketNewsAPI {
    @GET("news")
    suspend fun getStockMarketNews(
        @Query("category") category: String = "general",
        @Query("token") token: String = STOCK_API_KEY
    ): StockMarketNewsDto
}