package com.korugan.expansetrackerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.usecase.asset.InsertAssetUseCase
import com.korugan.expansetrackerapp.domain.usecase.crypto.CryptoTickerUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.DeleteFinancialUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.GetAllFinancialUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.GetNegativeFinancialUseCase
import com.korugan.expansetrackerapp.domain.usecase.financial.GetPositiveFinancialUseCase
import com.korugan.expansetrackerapp.domain.usecase.stock.StockQuoteUseCase
import com.korugan.expansetrackerapp.presentation.screens.crypto_details.CryptoTickerState
import com.korugan.expansetrackerapp.presentation.screens.stock_details.StockQuoteState
import com.korugan.expansetrackerapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.plus

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val insertAssetsUseCase: InsertAssetUseCase,
    private val cryptoTickerUseCase: CryptoTickerUseCase,
    private val stockQuoteUseCase: StockQuoteUseCase,
    private val getPositiveFinancialUseCase: GetPositiveFinancialUseCase,
    private val getNegativeFinancialUseCase: GetNegativeFinancialUseCase,
    private val deleteFinancialUseCase: DeleteFinancialUseCase
) : ViewModel() {

    init {
        getAllNegativeFinancial()
        getAllPositiveFinancial()
    }

    fun deleteFinancial(id : Int){
        viewModelScope.launch {
            deleteFinancialUseCase.invoke(id)
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

    fun insertAsset(assets: Assets) {
        viewModelScope.launch {
            insertAssetsUseCase(assets)
        }
    }

    private val _stockQuoteState = MutableStateFlow(StockQuoteState())
    val stockQuoteState: StateFlow<StockQuoteState> = _stockQuoteState.asStateFlow()

    fun getStockQuote(symbol: String) {
        viewModelScope.launch {
            stockQuoteUseCase.getStockQuote(symbol).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _stockQuoteState.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message ?: "Error"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _stockQuoteState.update {
                            it.copy(
                                isLoading = true,
                                error = ""
                            )
                        }
                    }

                    is Resource.Success -> {
                        _stockQuoteState.update {
                            it.copy(
                                isLoading = false,
                                stockQuote = it.stockQuote + (symbol to resource.data!!),
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }

    private val _cryptoTickerState = MutableStateFlow(CryptoTickerState())
    val cryptoTickerState: StateFlow<CryptoTickerState> = _cryptoTickerState.asStateFlow()

    fun getCryptoTicker(symbol: String) {
        viewModelScope.launch {
            cryptoTickerUseCase.getCryptoTickerById(symbol).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _cryptoTickerState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = resource.message ?: "Error"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _cryptoTickerState.update { state ->
                            state.copy(
                                isLoading = true,
                                error = ""
                            )
                        }
                    }

                    is Resource.Success -> {
                        _cryptoTickerState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = "",
                                cryptoTickers = state.cryptoTickers + (symbol to resource.data!!)
                            )
                        }
                    }
                }
            }
        }
    }
}