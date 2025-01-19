package com.korugan.expansetrackerapp.domain.usecase.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.search.dto.CryptoSearchDto
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
import com.korugan.expansetrackerapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoSearchUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    fun getCryptoSearchSymbols(q: String): Flow<Resource<CryptoSearchDto>> = flow {
        try {
            val crypto = cryptoRepository.getCryptoSearch(q)
            if (crypto.currencies.isNotEmpty()) {
                emit(Resource.Success(crypto))
            } else {
                emit(Resource.Error("No Stock Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: IOException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error : ${e}"))
        }
    }
}