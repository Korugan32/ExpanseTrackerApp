package com.korugan.expansetrackerapp.data.remote.stock.graph.dto

data class Historical(
    val adjClose: Double,
    val change: Double,
    val changeOverTime: Double,
    val changePercent: Double,
    val close: Double,
    val date: String,
    val high: Double,
    val label: String,
    val low: Double,
    val `open`: Double,
    val unadjustedVolume: Int,
    val volume: Int,
    val vwap: Double
)