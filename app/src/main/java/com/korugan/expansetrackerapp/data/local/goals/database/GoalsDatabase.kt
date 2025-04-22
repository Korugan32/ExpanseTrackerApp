package com.korugan.expansetrackerapp.data.local.goals.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.korugan.expansetrackerapp.data.local.date_converter.DateConverter
import com.korugan.expansetrackerapp.data.local.goals.dao.GoalsDao
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals

@Database(entities = [Goals::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class GoalsDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalsDao
    companion object {
        @Volatile
        private var INSTANCE: GoalsDatabase? = null

        fun getDatabase(context: Context): GoalsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalsDatabase::class.java,
                    "goals_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}