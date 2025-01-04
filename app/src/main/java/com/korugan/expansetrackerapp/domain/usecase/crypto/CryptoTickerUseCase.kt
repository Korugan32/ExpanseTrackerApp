package com.korugan.expansetrackerapp.domain.usecase.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.ticker.dto.CryptoTickerDto
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
import com.korugan.expansetrackerapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoTickerUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    fun getCryptoTickerById(id: String): Flow<Resource<CryptoTickerDto>> = flow {
        try {
            emit(Resource.Loading())
            val crypto = cryptoRepository.getCryptoTickerById(id)
            if (crypto.symbol.isNotEmpty()) {
                emit(Resource.Success(crypto))
            } else {
                emit(Resource.Error("No Stock Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: IOException) {
            emit(Resource.Error("Error : ${e}"))
        }
    }
}