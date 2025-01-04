package com.korugan.expansetrackerapp.data.remote.crypto.search

import com.korugan.expansetrackerapp.data.remote.crypto.search.dto.CryptoSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoSearchAPI {
    @GET("search")
    suspend fun getCryptoSearch(
        @Query("q") q: String
    ) : CryptoSearchDto
}