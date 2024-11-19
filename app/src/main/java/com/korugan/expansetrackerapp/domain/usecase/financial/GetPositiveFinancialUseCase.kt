package com.korugan.expansetrackerapp.domain.usecase.financial

import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.repository.financial.FinancialRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPositiveFinancialUseCase @Inject constructor(
    private val financialRepository: FinancialRepository
) {
    operator fun invoke(): Flow<List<Financials>> {
        return financialRepository.getPositiveFinancials()
    }
}