package com.korugan.expansetrackerapp.presentation.screens.crypto_details

import com.korugan.expansetrackerapp.data.remote.crypto.historical.dto.CryptoSymbolHistoricalDto

data class CryptoSymbolHistoryState(
    val isLoading: Boolean = false,
    val cryptoSymbolHistory: CryptoSymbolHistoricalDto? = null,
    val error: String = ""
)
