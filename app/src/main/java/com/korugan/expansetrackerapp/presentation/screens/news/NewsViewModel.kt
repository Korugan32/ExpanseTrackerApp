package com.korugan.expansetrackerapp.presentation.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.domain.usecase.stock.StockMarketNewsUseCase
import com.korugan.expansetrackerapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val stockMarketNewsUseCase: StockMarketNewsUseCase
) : ViewModel() {

    private val _stockMarketNewsState = MutableStateFlow(StockNewsState())
    val stockMarketNewsState: StateFlow<StockNewsState> = _stockMarketNewsState.asStateFlow()

    init {
        getStockMarketNews()
    }

    private fun getStockMarketNews() {
        viewModelScope.launch {
            stockMarketNewsUseCase.getStockMarketNews().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _stockMarketNewsState.value = _stockMarketNewsState.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _stockMarketNewsState.value = _stockMarketNewsState.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _stockMarketNewsState.value = _stockMarketNewsState.value.copy(
                            isLoading = false,
                            stockNews = resource.data,
                            error = ""
                        )
                    }
                }
            }
        }
    }
}