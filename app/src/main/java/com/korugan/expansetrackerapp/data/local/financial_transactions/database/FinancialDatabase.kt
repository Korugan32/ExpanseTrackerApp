package com.korugan.expansetrackerapp.data.local.financial_transactions.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.korugan.expansetrackerapp.data.local.date_converter.DateConverter
import com.korugan.expansetrackerapp.data.local.financial_transactions.dao.FinancialDao
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials


@Database(entities = [Financials::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class FinancialDatabase : RoomDatabase() {
    abstract fun financialDao(): FinancialDao
    companion object {
        @Volatile
        private var INSTANCE: FinancialDatabase? = null

        fun getDatabase(context: Context): FinancialDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FinancialDatabase::class.java,
                    "financial_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
