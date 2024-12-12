package com.korugan.expansetrackerapp.data.local.financial_transactions.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Financials(
    val amount : Double,
    val category : String,
    val symbol : String,
    val name : String,
    val description : String,
    val createDate : Date,
    @PrimaryKey(true)
    val id : Int = 0
)
