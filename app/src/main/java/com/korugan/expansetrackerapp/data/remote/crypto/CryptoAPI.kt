package com.korugan.expansetrackerapp.data.remote.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.dto.CryptoDto
import com.korugan.expansetrackerapp.util.Constants.CRYPTO_CONTENT_TYPE
import retrofit2.http.GET

interface CryptoAPI {
    @GET(CRYPTO_CONTENT_TYPE)
    suspend fun getCrypto(
    ): CryptoDto
}