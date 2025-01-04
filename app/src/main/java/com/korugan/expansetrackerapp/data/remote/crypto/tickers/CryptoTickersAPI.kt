package com.korugan.expansetrackerapp.data.remote.crypto.tickers

import com.korugan.expansetrackerapp.data.remote.crypto.tickers.dto.CryptoTickersDto
import retrofit2.http.GET

interface CryptoTickersAPI {
    @GET("tickers")
    suspend fun getCryptoTickers(
    ): CryptoTickersDto
}