package com.korugan.expansetrackerapp.domain.usecase.currency

import com.korugan.expansetrackerapp.data.mapper.currency.toCurrency
import com.korugan.expansetrackerapp.domain.model.currency.Currency
import com.korugan.expansetrackerapp.domain.repository.currency.CurrencyRepository
import com.korugan.expansetrackerapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {
    fun getCurrency(): Flow<Resource<List<Currency>>> = flow {
        try {
            emit(Resource.Loading())
            val currency = currencyRepository.getCurrency()
            val currencyData = currency.data
            if (currencyData.isNotEmpty()) {
                emit(Resource.Success(currency.toCurrency()))
            } else {
                emit(Resource.Error("No Currency Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: IOException) {
            emit(Resource.Error("Error : ${e}"))
        }
    }
}