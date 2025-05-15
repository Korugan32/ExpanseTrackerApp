package com.korugan.expansetrackerapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.usecase.asset.DeleteAssetUseCase
import com.korugan.expansetrackerapp.domain.usecase.asset.GetAllAssetUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.DeleteFinancialUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.GetAllFinancialUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.GetNegativeFinancialUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.GetPositiveFinancialUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllAssetUseCase: GetAllAssetUseCase,
    private val getAllFinancialUseCase: GetAllFinancialUseCase,
    private val getPositiveFinancialUseCase: GetPositiveFinancialUseCase,
    private val getNegativeFinancialUseCase: GetNegativeFinancialUseCase,
    private val deleteAssetUseCase: DeleteAssetUseCase,
    private val deleteFinancialUseCase: DeleteFinancialUseCase
) : ViewModel() {

    init {
        getAllFinancial()
        getAllNegativeFinancial()
        getAllPositiveFinancial()
        getAllAssets()
    }

    private val _allFinancial = MutableStateFlow<List<Financials>>(emptyList())
    val allFinancial : StateFlow<List<Financials>> = _allFinancial

    private fun getAllFinancial(){
        viewModelScope.launch {
            getAllFinancialUseCase.invoke().collect{
                _allFinancial.value = it
            }
        }
    }

    private val _allPositiveFinancial = MutableStateFlow<List<Financials>>(emptyList())
    val allPositiveFinancial : StateFlow<List<Financials>> = _allPositiveFinancial

    private fun getAllPositiveFinancial(){
        viewModelScope.launch {
            getPositiveFinancialUseCase.invoke().collect{
                _allPositiveFinancial.value = it
            }
        }
    }

    private val _allNegativeFinancial = MutableStateFlow<List<Financials>>(emptyList())
    val allNegativeFinancial : StateFlow<List<Financials>> = _allNegativeFinancial

    private fun getAllNegativeFinancial(){
        viewModelScope.launch {
            getNegativeFinancialUseCase.invoke().collect{
                _allNegativeFinancial.value = it
            }
        }
    }

    private val _allAssets = MutableStateFlow<List<Assets>>(emptyList())
    val allAssets : StateFlow<List<Assets>> = _allAssets

    private fun getAllAssets(){
        viewModelScope.launch {
            getAllAssetUseCase.invoke().collect{
                _allAssets.value = it
            }
        }
    }

    fun deleteAsset(id : Int){
        viewModelScope.launch {
            deleteAssetUseCase.invoke(id)
        }
    }

    fun deleteFinancial(id : Int){
        viewModelScope.launch {
            deleteFinancialUseCase.invoke(id)
        }
    }
}