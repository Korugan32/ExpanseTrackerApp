package com.korugan.expansetrackerapp.presentation.screens.adding_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.usecase.financial.InsertFinancialUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddingTransactionViewModel @Inject constructor(
    private val insertFinancialUseCase: InsertFinancialUseCase
) : ViewModel(){

    fun insertFinancial(financial : Financials){
        viewModelScope.launch {
            insertFinancialUseCase.invoke(financial)
        }
    }

}