package com.korugan.expansetrackerapp.domain.usecase.financial

import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.repository.financial.FinancialRepository
import javax.inject.Inject

class InsertFinancialUseCase @Inject constructor(
    private val financialRepository: FinancialRepository
) {
    suspend operator fun invoke(financials: Financials) {
        financialRepository.insertFinancial(financials)
    }
}