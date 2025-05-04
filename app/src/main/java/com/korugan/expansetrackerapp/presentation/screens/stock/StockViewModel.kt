package com.korugan.expansetrackerapp.presentation.screens.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.domain.usecase.stock.SearchStockSymbolsUseCase
import com.korugan.expansetrackerapp.domain.usecase.stock.StockSymbolsUseCase
import com.korugan.expansetrackerapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val stockSymbolsUseCase: StockSymbolsUseCase,
    private val searchStockSymbolsUseCase: SearchStockSymbolsUseCase
) : ViewModel() {

    private val _stockState = MutableStateFlow(StockSymbolsState())
    val stockState: StateFlow<StockSymbolsState> = _stockState.asStateFlow()

    init {
        getStockSymbols()
    }

    private fun getStockSymbols() {
        viewModelScope.launch {
            stockSymbolsUseCase.getStockSymbols().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _stockState.value = _stockState.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _stockState.value = _stockState.value.copy(
                            isLoading = false,
                            stockSymbols = resource.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _stockState.value = _stockState.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Error"
                        )
                    }
                }
            }
        }
    }

    private val _stockSearchState = MutableStateFlow(StockSearchState())
    val stockSearchState: StateFlow<StockSearchState> = _stockSearchState.asStateFlow()

    fun getStockSearchSymbols(q: String) {
        viewModelScope.launch {
            searchStockSymbolsUseCase.getStockSearchSymbols(q).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _stockSearchState.value = _stockSearchState.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _stockSearchState.value = _stockSearchState.value.copy(
                            isLoading = false,
                            stockSymbols = resource.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _stockSearchState.value = _stockSearchState.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Error"
                        )
                    }
                }
            }
        }
    }
}