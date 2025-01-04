package com.korugan.expansetrackerapp.domain.usecase.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.tickers.dto.CryptoTickersDto
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
import com.korugan.expansetrackerapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoTickersUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    fun getCryptoTickers(): Flow<Resource<CryptoTickersDto>> = flow {
        try {
            emit(Resource.Loading())
            val crypto = cryptoRepository.getCryptoTickers()
            if (crypto.isNotEmpty()) {
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