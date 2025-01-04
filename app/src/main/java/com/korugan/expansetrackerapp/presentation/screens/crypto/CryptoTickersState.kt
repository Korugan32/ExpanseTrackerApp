package com.korugan.expansetrackerapp.presentation.screens.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.tickers.dto.CryptoTickersDto

data class CryptoTickersState(
    val isLoading: Boolean = false,
    val cryptoTickers: CryptoTickersDto? = null,
    val error: String = ""
)
