package com.korugan.expansetrackerapp.data.remote.crypto.search.dto

data class CryptoSearchDto(
    val currencies: List<Currency>,
    val exchanges: List<Any>,
    val icos: List<Any>,
    val people: List<People>,
    val tags: List<Any>
)