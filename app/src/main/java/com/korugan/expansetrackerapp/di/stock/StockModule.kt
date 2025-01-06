package com.korugan.expansetrackerapp.di.stock

import com.korugan.expansetrackerapp.data.remote.stock.graph.StockSymbolGraphAPI
import com.korugan.expansetrackerapp.data.remote.stock.news.StockMarketNewsAPI
import com.korugan.expansetrackerapp.data.remote.stock.profile.StockProfileAPI
import com.korugan.expansetrackerapp.data.remote.stock.quote.StockQuoteAPI
import com.korugan.expansetrackerapp.data.remote.stock.recommendation.StockSymbolRecommendationAPI
import com.korugan.expansetrackerapp.data.remote.stock.symbol.StockSymbolAPI
import com.korugan.expansetrackerapp.data.remote.stock.symbolsearch.StockSymbolSearchAPI
import com.korugan.expansetrackerapp.data.repository.stock.StockRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.stock.StockRepository
import com.korugan.expansetrackerapp.util.Constants.BASE_STOCK_URL
import com.korugan.expansetrackerapp.util.Constants.STOCK_SYMBOL_GRAPH_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object StockModule {

    @Singleton
    @Provides
    fun provideStockQuoteApi(): StockQuoteAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_STOCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockQuoteAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockSymbolApi(): StockSymbolAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_STOCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockSymbolAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockProfileApi(): StockProfileAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_STOCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockProfileAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockMarketNewsApi(): StockMarketNewsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_STOCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockMarketNewsAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockSymbolSearchApi(): StockSymbolSearchAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_STOCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockSymbolSearchAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockSymbolRecommendationApi(): StockSymbolRecommendationAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_STOCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockSymbolRecommendationAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockSymbolGraphApi(): StockSymbolGraphAPI {
        return Retrofit.Builder()
            .baseUrl(STOCK_SYMBOL_GRAPH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockSymbolGraphAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockRepository(
        stockQuoteAPI: StockQuoteAPI,
        stockSymbolAPI: StockSymbolAPI,
        stockProfileAPI: StockProfileAPI,
        stockMarketNewsAPI: StockMarketNewsAPI,
        stockSymbolSearchAPI: StockSymbolSearchAPI,
        stockSymbolGraphAPI : StockSymbolGraphAPI,
        stockSymbolRecommendationAPI : StockSymbolRecommendationAPI
    ): StockRepository {
        return StockRepositoryImpl(
            stockMarketNewsAPI = stockMarketNewsAPI,
            stockQuoteAPI = stockQuoteAPI,
            stockSymbolAPI = stockSymbolAPI,
            stockProfileAPI = stockProfileAPI,
            stockSymbolSearchAPI = stockSymbolSearchAPI,
            stockSymbolGraphAPI = stockSymbolGraphAPI,
            stockSymbolRecommendationAPI = stockSymbolRecommendationAPI
        )
    }
}