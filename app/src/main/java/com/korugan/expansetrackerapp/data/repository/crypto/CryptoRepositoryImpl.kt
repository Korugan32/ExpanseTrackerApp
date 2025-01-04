package com.korugan.expansetrackerapp.data.repository.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.historical.CryptoSymbolHistoricalAPI
import com.korugan.expansetrackerapp.data.remote.crypto.historical.dto.CryptoSymbolHistoricalDto
import com.korugan.expansetrackerapp.data.remote.crypto.search.CryptoSearchAPI
import com.korugan.expansetrackerapp.data.remote.crypto.search.dto.CryptoSearchDto
import com.korugan.expansetrackerapp.data.remote.crypto.ticker.CryptoTickerAPI
import com.korugan.expansetrackerapp.data.remote.crypto.ticker.dto.CryptoTickerDto
import com.korugan.expansetrackerapp.data.remote.crypto.tickers.CryptoTickersAPI
import com.korugan.expansetrackerapp.data.remote.crypto.tickers.dto.CryptoTickersDto
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoTickerAPI: CryptoTickerAPI,
    private val cryptoTickersAPI: CryptoTickersAPI,
    private val cryptoSearchAPI: CryptoSearchAPI,
    private val cryptoSymbolHistoricalAPI: CryptoSymbolHistoricalAPI,
) : CryptoRepository {
    override suspend fun getCryptoTickers(): CryptoTickersDto {
        return cryptoTickersAPI.getCryptoTickers()
    }

    override suspend fun getCryptoTickerById(id: String): CryptoTickerDto {
        return cryptoTickerAPI.getCryptoTickerById(id)
    }

    override suspend fun getCryptoSearch(q: String): CryptoSearchDto {
        return cryptoSearchAPI.getCryptoSearch(q)
    }

    override suspend fun getCryptoSymbolHistorical(
        symbol: String,
        start: String,
        interval: String
    ): CryptoSymbolHistoricalDto {
        return cryptoSymbolHistoricalAPI.getCryptoSymbolHistorical(symbol, start, interval)
    }
}