package com.korugan.expansetrackerapp.domain.repository.financial

import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import kotlinx.coroutines.flow.Flow

interface FinancialRepository {
    fun getAllFinancials() : Flow<List<Financials>>
    suspend fun insertFinancial(financials: Financials)
    suspend fun deleteFinancialsById(id:Int)          
    fun getNegativeFinancials(): Flow<List<Financials>>
    fun getPositiveFinancials(): Flow<List<Financials>>
}