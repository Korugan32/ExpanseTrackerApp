package com.korugan.expansetrackerapp.data.repository.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.CryptoAPI
import com.korugan.expansetrackerapp.data.remote.crypto.dto.CryptoDto
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoAPI: CryptoAPI
) : CryptoRepository {
    override suspend fun getCrypto(): CryptoDto {
        return cryptoAPI.getCrypto()
    }
}