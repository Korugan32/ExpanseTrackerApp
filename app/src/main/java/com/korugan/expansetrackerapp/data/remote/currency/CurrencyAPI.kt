package com.korugan.expansetrackerapp.data.remote.currency

import com.korugan.expansetrackerapp.data.remote.currency.dto.CurrencyDto
import com.korugan.expansetrackerapp.util.Constants.CURRENCY_CONTENT_TYPE
import retrofit2.http.GET

interface CurrencyAPI {
    @GET(CURRENCY_CONTENT_TYPE)
    suspend fun getCrypto(
    ): CurrencyDto
}