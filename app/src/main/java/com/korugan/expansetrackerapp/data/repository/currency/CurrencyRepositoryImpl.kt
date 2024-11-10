package com.korugan.expansetrackerapp.data.repository.currency

import com.korugan.expansetrackerapp.data.remote.currency.CurrencyAPI
import com.korugan.expansetrackerapp.data.remote.currency.dto.CurrencyDto
import com.korugan.expansetrackerapp.domain.repository.currency.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyAPI: CurrencyAPI
) : CurrencyRepository {
    override suspend fun getCurrency(): CurrencyDto {
        return currencyAPI.getCrypto()
    }
}