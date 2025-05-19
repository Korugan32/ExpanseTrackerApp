package com.korugan.expansetrackerapp.data.local.subscription.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subscription(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val dayOfMonth: Int,
    val isAdded: Boolean = false
)

