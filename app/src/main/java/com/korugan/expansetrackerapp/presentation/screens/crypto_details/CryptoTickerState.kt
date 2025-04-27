package com.korugan.expansetrackerapp.presentation.screens.crypto_details

import com.korugan.expansetrackerapp.data.remote.crypto.ticker.dto.CryptoTickerDto

data class CryptoTickerState(
    val isLoading: Boolean = false,
    val cryptoTickers: Map<String, CryptoTickerDto> = emptyMap(),
    val error: String = ""
)
