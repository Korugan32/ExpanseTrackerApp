package com.korugan.expansetrackerapp.data.local.subscription.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.korugan.expansetrackerapp.data.local.subscription.dao.SubscriptionDao
import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription

@Database(entities = [Subscription::class], version = 1)
abstract class SubscriptionDatabase : RoomDatabase() {
    abstract fun SubscriptionDao(): SubscriptionDao
    companion object {
        @Volatile
        private var INSTANCE: SubscriptionDatabase? = null

        fun getDatabase(context: Context): SubscriptionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubscriptionDatabase::class.java,
                    "subscription_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}