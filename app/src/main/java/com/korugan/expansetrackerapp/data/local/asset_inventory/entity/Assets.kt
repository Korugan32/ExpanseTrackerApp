package com.korugan.expansetrackerapp.data.local.asset_inventory.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Assets(
    val category : Int,
    val amount: Double,
    val purchasePrice: Double,
    val symbol: String,
    val name: String,
    val description: String,
    val createDate: Date,
    @PrimaryKey(true)
    val id: Int = 0,
)