package com.korugan.expansetrackerapp.data.remote.stock.recommendation

import com.korugan.expansetrackerapp.data.remote.stock.recommendation.dto.StockSymbolRecommendationDto
import com.korugan.expansetrackerapp.util.Constants.STOCK_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface StockSymbolRecommendationAPI {
    @GET("stock/recommendation")
    suspend fun getStockSymbolRecommendation(
        @Query("symbol") symbol: String,
        @Query("token") token: String = STOCK_API_KEY
    ): StockSymbolRecommendationDto
}