package com.korugan.expansetrackerapp.data.remote.stock.profile

import com.korugan.expansetrackerapp.data.remote.stock.profile.dto.StockProfileDto
import com.korugan.expansetrackerapp.util.Constants.STOCK_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface StockProfileAPI {
    @GET("stock/profile2")
    suspend fun getStockProfile(
        @Query("symbol") symbol: String,
        @Query("token") token: String = STOCK_API_KEY
    ): StockProfileDto
}