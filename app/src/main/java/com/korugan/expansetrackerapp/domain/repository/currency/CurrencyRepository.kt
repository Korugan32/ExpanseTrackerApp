package com.korugan.expansetrackerapp.domain.repository.currency

import com.korugan.expansetrackerapp.data.remote.currency.dto.CurrencyDto

interface CurrencyRepository {
    suspend fun getCurrency() : CurrencyDto
}