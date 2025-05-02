package com.korugan.expansetrackerapp.data.local.goals.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Goals (
    val title : String,
    val description : String,
    val deadline : Date,
    val progress : Int,
    val status : String,
    val price : Double,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)
