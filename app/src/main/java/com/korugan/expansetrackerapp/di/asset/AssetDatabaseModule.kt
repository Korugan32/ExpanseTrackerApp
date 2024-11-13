package com.korugan.expansetrackerapp.di.asset

import android.content.Context
import androidx.room.Room
import com.korugan.expansetrackerapp.data.local.asset_inventory.dao.AssetsDao
import com.korugan.expansetrackerapp.data.local.asset_inventory.database.AssetsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AssetDatabaseModule {

    @Singleton
    @Provides
    fun provideAssetsDatabase(@ApplicationContext context: Context): AssetsDatabase {
        return Room.databaseBuilder(
            context,
            AssetsDatabase::class.java,
            "assets_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAssetsDao(assetsDatabase: AssetsDatabase): AssetsDao {
        return assetsDatabase.assetsDao()
    }
}