package com.korugan.expansetrackerapp.domain.repository.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.historical.dto.CryptoSymbolHistoricalDto
import com.korugan.expansetrackerapp.data.remote.crypto.search.dto.CryptoSearchDto
import com.korugan.expansetrackerapp.data.remote.crypto.ticker.dto.CryptoTickerDto
import com.korugan.expansetrackerapp.data.remote.crypto.tickers.dto.CryptoTickersDto

interface CryptoRepository {
    suspend fun getCryptoTickers() : CryptoTickersDto
    suspend fun getCryptoTickerById(id : String) : CryptoTickerDto
    suspend fun getCryptoSearch(q : String) : CryptoSearchDto
    suspend fun getCryptoSymbolHistorical(symbol : String, start : String, interval : String) : CryptoSymbolHistoricalDto
}