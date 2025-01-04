package com.korugan.expansetrackerapp.data.remote.crypto.search.dto

data class Currency(
    val contract_address: List<ContractAddres>,
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val rev: Int,
    val symbol: String,
    val type: String
)