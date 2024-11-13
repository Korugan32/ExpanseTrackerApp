package com.korugan.expansetrackerapp.domain.usecase.crypto

import com.korugan.expansetrackerapp.data.mapper.crypto.toCrypto
import com.korugan.expansetrackerapp.domain.model.crypto.Crypto
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
import com.korugan.expansetrackerapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    fun getCrypto(): Flow<Resource<List<Crypto>>> = flow {
        try {
            emit(Resource.Loading())
            val crypto = cryptoRepository.getCrypto()
            if (crypto.data.isNotEmpty()) {
                emit(Resource.Success(crypto.toCrypto()))
            } else {
                emit(Resource.Error("No Crypto Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: IOException) {
            emit(Resource.Error("Error : ${e}"))
        }
    }
}
