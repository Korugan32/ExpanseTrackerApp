package com.korugan.expansetrackerapp.data.repository.stock

import com.korugan.expansetrackerapp.data.remote.stock.graph.StockSymbolGraphAPI
import com.korugan.expansetrackerapp.data.remote.stock.graph.dto.StockSymbolGraphDto
import com.korugan.expansetrackerapp.data.remote.stock.news.StockMarketNewsAPI
import com.korugan.expansetrackerapp.data.remote.stock.news.dto.StockMarketNewsDto
import com.korugan.expansetrackerapp.data.remote.stock.profile.StockProfileAPI
import com.korugan.expansetrackerapp.data.remote.stock.profile.dto.StockProfileDto
import com.korugan.expansetrackerapp.data.remote.stock.quote.StockQuoteAPI
import com.korugan.expansetrackerapp.data.remote.stock.quote.dto.StockQuoteDto
import com.korugan.expansetrackerapp.data.remote.stock.recommendation.StockSymbolRecommendationAPI
import com.korugan.expansetrackerapp.data.remote.stock.recommendation.dto.StockSymbolRecommendationDto
import com.korugan.expansetrackerapp.data.remote.stock.symbol.StockSymbolAPI
import com.korugan.expansetrackerapp.data.remote.stock.symbol.dto.StockSymbolDto
import com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.StockSymbolSearchAPI
import com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.dto.StockSymbolSearchDto
import com.korugan.expansetrackerapp.domain.repository.stock.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val stockMarketNewsAPI: StockMarketNewsAPI,
    private val stockSymbolAPI: StockSymbolAPI,
    private val stockQuoteAPI: StockQuoteAPI,
    private val stockProfileAPI: StockProfileAPI,
    private val stockSymbolSearchAPI: StockSymbolSearchAPI,
    private val stockSymbolGraphAPI: StockSymbolGraphAPI,
    private val stockSymbolRecommendationAPI : StockSymbolRecommendationAPI
) : StockRepository {
    override suspend fun getStockMarketNews(): StockMarketNewsDto {
        return stockMarketNewsAPI.getStockMarketNews()
    }

    override suspend fun getStockProfile(symbol: String): StockProfileDto {
        return stockProfileAPI.getStockProfile(symbol)
    }

    override suspend fun getStockQuote(symbol: String): StockQuoteDto {
        return stockQuoteAPI.getStockQuote(symbol)
    }

    override suspend fun getStockSymbols(): StockSymbolDto {
        return stockSymbolAPI.getStockSymbols()
    }

    override suspend fun getSearchStockSymbols(q: String): StockSymbolSearchDto {
        return stockSymbolSearchAPI.getSearchStockSymbols(q)
    }

    override suspend fun getStockSymbolGraph(symbol: String): StockSymbolGraphDto {
        return stockSymbolGraphAPI.getStockSymbolGraph(symbol)
    }

    override suspend fun getStockSymbolRecommendation(symbol: String): StockSymbolRecommendationDto {
        return stockSymbolRecommendationAPI.getStockSymbolRecommendation(symbol)
    }
}