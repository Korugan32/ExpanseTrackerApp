package com.korugan.expansetrackerapp.domain.repository.onboarding

interface OnboardingRepository {
    suspend fun onBoardingComplete(): Boolean
    suspend fun setOnBoardingComplete(isComplete: Boolean)
}