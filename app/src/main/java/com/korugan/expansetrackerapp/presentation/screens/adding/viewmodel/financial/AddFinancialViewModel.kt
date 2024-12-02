package com.korugan.expansetrackerapp.presentation.screens.adding.viewmodel.financial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.usecase.financial.InsertFinancialUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFinancialViewModel @Inject constructor(
    private val insertFinancialUseCase: InsertFinancialUseCase
) : ViewModel() {

    fun addFinancial(financials: Financials) {
        viewModelScope.launch {
            insertFinancialUseCase(financials)
        }
    }

}