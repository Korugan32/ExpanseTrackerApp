package com.korugan.expansetrackerapp.data.repository.financial

import com.korugan.expansetrackerapp.data.local.financial_transactions.dao.FinancialDao
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.repository.financial.FinancialRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FinancialRepositoryImpl @Inject constructor(private val financialDao: FinancialDao) : FinancialRepository {
    override fun getAllFinancials(): Flow<List<Financials>> {
        return financialDao.getAllFinancials()
    }

    override suspend fun insertFinancial(financials: Financials) {
        financialDao.insertFinancial(financials)
    }

    override suspend fun deleteFinancialsById(id: Int) {
        financialDao.deleteFinancialsById(id)
    }

    override fun getNegativeFinancials(): Flow<List<Financials>> {
        return financialDao.getNegativeFinancials()
    }

    override fun getPositiveFinancials(): Flow<List<Financials>> {
        return financialDao.getPositiveFinancials()
    }
}