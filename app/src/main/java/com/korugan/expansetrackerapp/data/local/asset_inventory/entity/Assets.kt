package com.korugan.expansetrackerapp.data.local.asset_inventory.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Assets(
    val amount : Double,
    val purchasePrice : Double,
    val symbol : String,
    val name : String,
    val description : String,
    @PrimaryKey(true)
    val id : Int = 0
)