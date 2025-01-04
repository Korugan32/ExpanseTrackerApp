package com.korugan.expansetrackerapp.presentation.screens.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.domain.usecase.crypto.CryptoSearchUseCase
import com.korugan.expansetrackerapp.domain.usecase.crypto.CryptoTickersUseCase
import com.korugan.expansetrackerapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val cryptoSearchUseCase: CryptoSearchUseCase,
    private val cryptoTickersUseCase: CryptoTickersUseCase
) : ViewModel() {

    private val _cryptoTickersState = MutableStateFlow(CryptoTickersState())
    val cryptoTickersState: StateFlow<CryptoTickersState> = _cryptoTickersState.asStateFlow()

    init {
        getCryptoTickers()
    }

    private fun getCryptoTickers() {
        viewModelScope.launch {
            cryptoTickersUseCase.getCryptoTickers().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _cryptoTickersState.value = _cryptoTickersState.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _cryptoTickersState.value = _cryptoTickersState.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _cryptoTickersState.value = _cryptoTickersState.value.copy(
                            isLoading = false,
                            cryptoTickers = resource.data,
                            error = ""
                        )
                    }
                }
            }
        }
    }

    private val _cryptoSearchState = MutableStateFlow(CryptoSearchState())
    val cryptoSearchState: StateFlow<CryptoSearchState> = _cryptoSearchState.asStateFlow()

    fun getCryptoSearch(q: String) {
        viewModelScope.launch {
            cryptoSearchUseCase.getCryptoSearchSymbols(q).collect { resoruce ->
                when (resoruce) {
                    is Resource.Error -> {
                        _cryptoSearchState.value = _cryptoSearchState.value.copy(
                            isLoading = false,
                            error = resoruce.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _cryptoSearchState.value = _cryptoSearchState.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _cryptoSearchState.value = _cryptoSearchState.value.copy(
                            isLoading = false,
                            cryptoSearch = resoruce.data,
                            error = ""
                        )
                    }
                }
            }
        }
    }
}