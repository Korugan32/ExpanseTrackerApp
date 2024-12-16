package com.korugan.expansetrackerapp.data.remote.stock.data

import com.korugan.expansetrackerapp.data.remote.stock.data.dto.StockDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StockDataAPI {
    @GET("borsa/hisseyuzeysel")
    suspend fun getStockData(
        @Query("stock_symbol") query : String
    ) : StockDataDto
}