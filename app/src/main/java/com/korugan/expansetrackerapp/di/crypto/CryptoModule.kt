package com.korugan.expansetrackerapp.di.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.historical.CryptoSymbolHistoricalAPI
import com.korugan.expansetrackerapp.data.remote.crypto.search.CryptoSearchAPI
import com.korugan.expansetrackerapp.data.remote.crypto.ticker.CryptoTickerAPI
import com.korugan.expansetrackerapp.data.remote.crypto.tickers.CryptoTickersAPI
import com.korugan.expansetrackerapp.data.repository.crypto.CryptoRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
import com.korugan.expansetrackerapp.util.Constants.CRYPTO_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CryptoModule {

    @Singleton
    @Provides
    fun provideCryptoTickerApi(): CryptoTickerAPI {
        return Retrofit.Builder()
            .baseUrl(CRYPTO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoTickerAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideCryptoTickersApi(): CryptoTickersAPI {
        return Retrofit.Builder()
            .baseUrl(CRYPTO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoTickersAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideCryptoSearchApi(): CryptoSearchAPI {
        return Retrofit.Builder()
            .baseUrl(CRYPTO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoSearchAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideCryptoSymbolHistoricalApi(): CryptoSymbolHistoricalAPI {
        return Retrofit.Builder()
            .baseUrl(CRYPTO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoSymbolHistoricalAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideCryptoRepository(
        cryptoTickerAPI: CryptoTickerAPI,
        cryptoSearchAPI: CryptoSearchAPI,
        cryptoTickersAPI: CryptoTickersAPI,
        cryptoSymbolHistoricalAPI: CryptoSymbolHistoricalAPI
    ): CryptoRepository {
        return CryptoRepositoryImpl(
            cryptoTickerAPI = cryptoTickerAPI,
            cryptoSearchAPI = cryptoSearchAPI,
            cryptoTickersAPI = cryptoTickersAPI,
            cryptoSymbolHistoricalAPI = cryptoSymbolHistoricalAPI,
        )
    }
}