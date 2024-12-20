package com.korugan.expansetrackerapp.data.repository.onboarding

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.korugan.expansetrackerapp.domain.repository.onboarding.OnboardingRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingRepository {

    companion object {
        private val ONBOARDING_KEY = booleanPreferencesKey("onboarding_complete")
    }

    override suspend fun onBoardingComplete(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[ONBOARDING_KEY] ?: false
    }

    override suspend fun setOnBoardingComplete(isComplete: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = isComplete
        }
    }
}