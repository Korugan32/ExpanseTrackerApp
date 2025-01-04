package com.korugan.expansetrackerapp.presentation.screens.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.search.dto.CryptoSearchDto

data class CryptoSearchState(
    val isLoading: Boolean = false,
    val cryptoSearch: CryptoSearchDto? = null,
    val error: String = ""
)
