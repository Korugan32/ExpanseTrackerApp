package com.korugan.expansetrackerapp.data.remote.stock.symbol

import com.korugan.expansetrackerapp.data.remote.stock.symbol.dto.StockSymbolDto
import com.korugan.expansetrackerapp.util.Constants.STOCK_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface StockSymbolAPI {
    @GET("stock/symbol")
    suspend fun getStockSymbols(
        @Query("exchange") exchange: String = "US",
        @Query("token") token: String = STOCK_API_KEY
    ): StockSymbolDto
}