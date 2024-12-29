package com.korugan.expansetrackerapp.data.remote.stock.symbolsearch

import com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.dto.StockSymbolSearchDto
import com.korugan.expansetrackerapp.util.Constants.STOCK_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface StockSymbolSearchAPI {
    @GET("search")
    suspend fun getSearchStockSymbols(
        @Query("q") query: String,
        @Query("exchange") exchange: String = "US",
        @Query("token") token: String = STOCK_API_KEY
    ): StockSymbolSearchDto
}