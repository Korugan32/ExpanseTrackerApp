package com.korugan.expansetrackerapp.presentation.screens.crypto_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.domain.usecase.crypto.CryptoSymbolHistoryUseCase
import com.korugan.expansetrackerapp.domain.usecase.crypto.CryptoTickerUseCase
import com.korugan.expansetrackerapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val cryptoTickerUseCase: CryptoTickerUseCase,
    private val cryptoSymbolHistoryUseCase: CryptoSymbolHistoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    init {
        savedStateHandle.get<String>("symbol")?.let { symbol ->
            getCryptoTicker(symbol)
            getCryptoSymbolHistory(symbol)
        }
    }

    private val _cryptoTickerState = MutableStateFlow(CryptoTickerState())
    val cryptoTickerState: StateFlow<CryptoTickerState> = _cryptoTickerState.asStateFlow()

    fun getCryptoTicker(symbol: String) {
        viewModelScope.launch {
            cryptoTickerUseCase.getCryptoTickerById(symbol).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _cryptoTickerState.value = _cryptoTickerState.value.copy(
                            isLoading = false, error = resource.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _cryptoTickerState.value = _cryptoTickerState.value.copy(
                            isLoading = true, error = ""
                        )
                    }

                    is Resource.Success -> {
                        _cryptoTickerState.value = _cryptoTickerState.value.copy(
                            isLoading = false, cryptoTickers = _cryptoTickerState.value.cryptoTickers + (symbol to (resource.data ?: return@collect)) , error = ""
                        )
                    }
                }
            }
        }
    }

    private val _cryptoSymbolHistoryState = MutableStateFlow(CryptoSymbolHistoryState())
    val cryptoSymbolHistoryState: StateFlow<CryptoSymbolHistoryState> =
        _cryptoSymbolHistoryState.asStateFlow()

    fun getCryptoSymbolHistory(symbol: String, interval: String = "7d") {
        val date = LocalDate.now().minusYears(1).plusDays(2).toString()
        viewModelScope.launch {
            cryptoSymbolHistoryUseCase.getCryptoSymbolHistorical(symbol, date, interval)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _cryptoSymbolHistoryState.value = _cryptoSymbolHistoryState.value.copy(
                                isLoading = false, error = resource.message ?: "Error"
                            )
                        }

                        is Resource.Loading -> {
                            _cryptoSymbolHistoryState.value = _cryptoSymbolHistoryState.value.copy(
                                isLoading = true, error = ""
                            )
                        }

                        is Resource.Success -> {
                            _cryptoSymbolHistoryState.value = _cryptoSymbolHistoryState.value.copy(
                                isLoading = false, cryptoSymbolHistory = resource.data, error = ""
                            )
                        }
                    }
                }
        }
    }
}