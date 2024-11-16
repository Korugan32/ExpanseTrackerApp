package com.korugan.expansetrackerapp.di.currency

import com.korugan.expansetrackerapp.data.remote.currency.CurrencyAPI
import com.korugan.expansetrackerapp.data.repository.currency.CurrencyRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.currency.CurrencyRepository
import com.korugan.expansetrackerapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CurrencyModule {

    @Singleton
    @Provides
    fun provideCurrencyApi() : CurrencyAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyRepository(currencyAPI: CurrencyAPI) : CurrencyRepository {
        return CurrencyRepositoryImpl(currencyAPI)
    }
}