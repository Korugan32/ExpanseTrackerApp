package com.korugan.expansetrackerapp.domain.repository.stock

import com.korugan.expansetrackerapp.data.remote.stock.graph.dto.StockSymbolGraphDto
import com.korugan.expansetrackerapp.data.remote.stock.news.dto.StockMarketNewsDto
import com.korugan.expansetrackerapp.data.remote.stock.profile.dto.StockProfileDto
import com.korugan.expansetrackerapp.data.remote.stock.quote.dto.StockQuoteDto
import com.korugan.expansetrackerapp.data.remote.stock.recommendation.dto.StockSymbolRecommendationDto
import com.korugan.expansetrackerapp.data.remote.stock.symbol.dto.StockSymbolDto
import com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.dto.StockSymbolSearchDto

interface StockRepository {
    suspend fun getStockMarketNews() : StockMarketNewsDto
    suspend fun getStockProfile(symbol : String) : StockProfileDto
    suspend fun getStockQuote(symbol : String) : StockQuoteDto
    suspend fun getStockSymbols() : StockSymbolDto
    suspend fun getSearchStockSymbols(q : String) : StockSymbolSearchDto
    suspend fun getStockSymbolGraph(symbol : String) : StockSymbolGraphDto
    suspend fun getStockSymbolRecommendation(symbol: String) : StockSymbolRecommendationDto
}