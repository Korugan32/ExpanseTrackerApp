package com.korugan.expansetrackerapp.presentation.screens.stock_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.domain.usecase.stock.StockProfileUseCase
import com.korugan.expansetrackerapp.domain.usecase.stock.StockQuoteUseCase
import com.korugan.expansetrackerapp.domain.usecase.stock.StockSymbolGraphUseCase
import com.korugan.expansetrackerapp.domain.usecase.stock.StockSymbolRecommendationUseCase
import com.korugan.expansetrackerapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockDetailsViewModel @Inject constructor(
    private val stockSymbolGraphUseCase: StockSymbolGraphUseCase,
    private val stockProfileUseCase: StockProfileUseCase,
    private val stockQuoteUseCase: StockQuoteUseCase,
    private val stockSymbolRecommendationUseCase: StockSymbolRecommendationUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _stockSymbolGraphState = MutableStateFlow(StockSymbolGraphState())
    val stockSymbolGraphState: StateFlow<StockSymbolGraphState> =
        _stockSymbolGraphState.asStateFlow()

    init {
        savedStateHandle.get<String>("symbol")?.let { symbol ->
            getStockProfile(symbol)
            getStockSymbolGraph(symbol)
            getStockQuote(symbol)
            getStockRecommendation(symbol)
        }
    }

    private fun getStockSymbolGraph(symbol: String) {
        viewModelScope.launch {
            stockSymbolGraphUseCase.getStockSymbolGraph(symbol).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _stockSymbolGraphState.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message ?: "Error"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _stockSymbolGraphState.update {
                            it.copy(
                                isLoading = true,
                                error = ""
                            )
                        }
                    }

                    is Resource.Success -> {
                        _stockSymbolGraphState.update {
                            it.copy(
                                isLoading = false,
                                stockSymbolGraph = resource.data,
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }

    private val _stockProfileState = MutableStateFlow(StockProfileState())
    val stockProfileState: StateFlow<StockProfileState> = _stockProfileState.asStateFlow()

    private fun getStockProfile(symbol: String) {
        viewModelScope.launch {
            stockProfileUseCase.getStockProfile(symbol).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _stockProfileState.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message ?: "Error"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _stockProfileState.update {
                            it.copy(
                                isLoading = true,
                                error = ""
                            )
                        }
                    }

                    is Resource.Success -> {
                        _stockProfileState.update {
                            it.copy(
                                isLoading = false,
                                stockProfile = resource.data,
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }

    private val _stockQuoteState = MutableStateFlow(StockQuoteState())
    val stockQuoteState: StateFlow<StockQuoteState> = _stockQuoteState.asStateFlow()

    private fun getStockQuote(symbol: String) {
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
                                stockQuote = _stockQuoteState.value.stockQuote + (symbol to (resource.data ?: return@collect)),
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }

    private val _stockRecommendationState = MutableStateFlow(StockRecommendationState())
    val stockRecommendationState: StateFlow<StockRecommendationState> =
        _stockRecommendationState.asStateFlow()

    private fun getStockRecommendation(symbol: String) {
        viewModelScope.launch {
            stockSymbolRecommendationUseCase.getStockRecommendation(symbol).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _stockRecommendationState.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message ?: "Error"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _stockRecommendationState.update {
                            it.copy(
                                isLoading = true,
                                error = ""
                            )
                        }
                    }

                    is Resource.Success -> {
                        _stockRecommendationState.update {
                            it.copy(
                                isLoading = false,
                                stockRecommendation = resource.data,
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }
}