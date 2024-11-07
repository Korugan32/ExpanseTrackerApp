package com.korugan.expansetrackerapp.domain.repository.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.dto.CryptoDto

interface CryptoRepository {
    suspend fun getCrypto() : CryptoDto
}