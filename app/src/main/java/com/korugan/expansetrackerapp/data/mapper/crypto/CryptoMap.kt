package com.korugan.expansetrackerapp.data.mapper.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.dto.CryptoDto
import com.korugan.expansetrackerapp.domain.model.crypto.Crypto

fun CryptoDto.toCrypto() : List<Crypto>{
    return data.map {
        Crypto(
            changePercent24Hr = it.changePercent24Hr,
            id = it.name,
            name = it.name,
            priceUsd = it.priceUsd,
            symbol = it.symbol
        )
    }
}