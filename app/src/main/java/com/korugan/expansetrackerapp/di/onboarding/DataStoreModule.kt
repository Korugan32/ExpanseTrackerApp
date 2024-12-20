package com.korugan.expansetrackerapp.di.onboarding

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val DATASTORE_NAME = "onboarding_preferences"

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}