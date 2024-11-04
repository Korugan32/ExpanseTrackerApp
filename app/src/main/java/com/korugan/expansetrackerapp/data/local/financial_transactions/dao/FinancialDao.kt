package com.korugan.expansetrackerapp.data.local.financial_transactions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import kotlinx.coroutines.flow.Flow

@Dao
interface FinancialDao {
    @Query("SELECT * FROM Financials")
    fun getAllFinancials() : Flow<List<Financials>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFinancial(financials: Financials)

    @Query("DELETE FROM Financials WHERE id = :id")
    suspend fun deleteFinancialsById(id:Int)

    @Query("SELECT * FROM Financials WHERE amount < 0")
    fun getNegativeFinancials(): Flow<List<Financials>>

    @Query("SELECT * FROM Financials WHERE amount > 0")
    fun getPositiveFinancials(): Flow<List<Financials>>
}