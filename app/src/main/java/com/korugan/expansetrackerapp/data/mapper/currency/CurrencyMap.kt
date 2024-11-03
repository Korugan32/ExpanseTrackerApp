package com.korugan.expansetrackerapp.data.mapper.currency

import com.korugan.expansetrackerapp.data.remote.currency.dto.CurrencyDto
import com.korugan.expansetrackerapp.domain.model.currency.Currency

fun CurrencyDto.toCurrency() : List<Currency>{
    return data.map {
        Currency(
            symbol = it.symbol,
            rateUsd = it.rateUsd
        )
    }
}