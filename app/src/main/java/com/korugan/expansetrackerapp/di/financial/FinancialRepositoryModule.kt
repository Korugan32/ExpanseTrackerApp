package com.korugan.expansetrackerapp.di.financial

import com.korugan.expansetrackerapp.data.repository.financial.FinancialRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.financial.FinancialRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class FinancialRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFinancialRepository(
       financialRepositoryImpl: FinancialRepositoryImpl
    ): FinancialRepository
}