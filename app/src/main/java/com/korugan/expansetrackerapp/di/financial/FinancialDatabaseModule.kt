package com.korugan.expansetrackerapp.di.financial

import android.content.Context
import androidx.room.Room
import com.korugan.expansetrackerapp.data.local.financial_transactions.dao.FinancialDao
import com.korugan.expansetrackerapp.data.local.financial_transactions.database.FinancialDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FinancialDatabaseModule {

    @Provides
    @Singleton
    fun provideFinancialDatabase(@ApplicationContext context: Context): FinancialDatabase {
        return Room.databaseBuilder(
            context,
            FinancialDatabase::class.java,
            "financial_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFinancialDao(financialDatabase: FinancialDatabase): FinancialDao {
        return financialDatabase.financialDao()
    }

}