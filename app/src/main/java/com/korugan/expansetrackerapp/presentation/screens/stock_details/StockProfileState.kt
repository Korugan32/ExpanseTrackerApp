package com.korugan.expansetrackerapp.presentation.screens.stock_details

import com.korugan.expansetrackerapp.data.remote.stock.profile.dto.StockProfileDto

data class StockProfileState(
    val isLoading: Boolean = false,
    val stockProfile: StockProfileDto? = null,
    val error : String = ""
)