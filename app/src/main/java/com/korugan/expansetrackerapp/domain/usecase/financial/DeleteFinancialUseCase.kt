package com.korugan.expansetrackerapp.domain.usecase.financial

import com.korugan.expansetrackerapp.domain.repository.financial.FinancialRepository
import javax.inject.Inject

class DeleteFinancialUseCase @Inject constructor(
    private val financialRepository: FinancialRepository
) {
    suspend operator fun invoke(id: Int) {
        financialRepository.deleteFinancialsById(id)
    }
}