package com.korugan.expansetrackerapp.data.remote.stock.graph

import com.korugan.expansetrackerapp.data.remote.stock.graph.dto.StockSymbolGraphDto
import com.korugan.expansetrackerapp.util.Constants.STOCK_SYMBOL_GRAPH_API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockSymbolGraphAPI {
    @GET("historical-price-full/{symbol}")
    suspend fun getStockSymbolGraph(
        @Path("symbol") symbol : String,
        @Query("apikey") apikey : String = STOCK_SYMBOL_GRAPH_API_KEY,
    ) : StockSymbolGraphDto
}