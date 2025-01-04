package com.korugan.expansetrackerapp.data.remote.crypto.historical

import com.korugan.expansetrackerapp.data.remote.crypto.historical.dto.CryptoSymbolHistoricalDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoSymbolHistoricalAPI {
    @GET("tickers/{symbol}/historical")
    suspend fun getCryptoSymbolHistorical(
        @Path("symbol") symbol : String,
        @Query("start") date : String,
        @Query("interval") interval : String
    ) : CryptoSymbolHistoricalDto
}