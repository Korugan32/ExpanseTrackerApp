package com.korugan.expansetrackerapp.domain.usecase.stock

import com.korugan.expansetrackerapp.data.remote.stock.quote.dto.StockQuoteDto
import com.korugan.expansetrackerapp.data.remote.stock.symbol.dto.StockSymbolDto
import com.korugan.expansetrackerapp.domain.repository.stock.StockRepository
import com.korugan.expansetrackerapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StockQuoteUseCase @Inject constructor(private val stockRepository: StockRepository) {
    fun getStockQuote(symbol: String): Flow<Resource<StockQuoteDto>> = flow {
        try {
            emit(Resource.Loading())
            val stock = stockRepository.getStockQuote(symbol)
            if (stock.c.isFinite()) {
                emit(Resource.Success(stock))
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