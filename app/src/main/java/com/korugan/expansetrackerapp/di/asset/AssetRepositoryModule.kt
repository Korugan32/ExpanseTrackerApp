package com.korugan.expansetrackerapp.di.asset

import com.korugan.expansetrackerapp.data.repository.assets.AssetsRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.assets.AssetsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AssetRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAssetRepository(
        assetsRepositoryImpl: AssetsRepositoryImpl
    ): AssetsRepository
}