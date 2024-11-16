package com.korugan.expansetrackerapp.di.crypto

import com.korugan.expansetrackerapp.data.remote.crypto.CryptoAPI
import com.korugan.expansetrackerapp.data.repository.crypto.CryptoRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.crypto.CryptoRepository
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
object CryptoModule {

    @Singleton
    @Provides
    fun provideCryptoApi(): CryptoAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideCryptoRepository(cryptoAPI: CryptoAPI): CryptoRepository {
        return CryptoRepositoryImpl(cryptoAPI)
    }
}