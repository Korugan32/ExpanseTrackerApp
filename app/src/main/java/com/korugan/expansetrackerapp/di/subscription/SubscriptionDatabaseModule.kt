package com.korugan.expansetrackerapp.di.subscription

import android.content.Context
import androidx.room.Room
import com.korugan.expansetrackerapp.data.local.subscription.dao.SubscriptionDao
import com.korugan.expansetrackerapp.data.local.subscription.database.SubscriptionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SubscriptionDatabaseModule {

    @Provides
    @Singleton
    fun provideSubscriptionDatabase(@ApplicationContext context: Context): SubscriptionDatabase {
        return Room.databaseBuilder(
            context,
            SubscriptionDatabase::class.java,
            "subscription_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSubscriptionDao(subscriptionDatabase: SubscriptionDatabase): SubscriptionDao {
        return subscriptionDatabase.SubscriptionDao()
    }

}