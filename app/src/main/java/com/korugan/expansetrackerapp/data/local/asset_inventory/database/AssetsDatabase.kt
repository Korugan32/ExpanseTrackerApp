package com.korugan.expansetrackerapp.data.local.asset_inventory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.korugan.expansetrackerapp.data.local.asset_inventory.dao.AssetsDao
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.data.local.date_converter.DateConverter

@Database(entities = [Assets::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AssetsDatabase : RoomDatabase() {

    abstract fun assetsDao(): AssetsDao

    companion object {
        @Volatile
        private var INSTANCE: AssetsDatabase? = null

        fun getDatabase(context: Context): AssetsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AssetsDatabase::class.java,
                    "assets_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}