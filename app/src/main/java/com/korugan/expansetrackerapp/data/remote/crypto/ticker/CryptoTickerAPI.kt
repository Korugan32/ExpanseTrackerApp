package com.korugan.expansetrackerapp.data.remote.crypto.ticker

import com.korugan.expansetrackerapp.data.remote.crypto.ticker.dto.CryptoTickerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoTickerAPI {
    @GET("tickers/{id}")
    suspend fun getCryptoTickerById(
        @Path("id") id : String
    ) : CryptoTickerDto
}