package com.korugan.expansetrackerapp.data.remote.stock.list

import com.korugan.expansetrackerapp.data.remote.stock.list.dto.StockListDto
import retrofit2.http.GET

interface StockListAPI {
    @GET("hisse/list")
    suspend fun getStockList() : StockListDto
}