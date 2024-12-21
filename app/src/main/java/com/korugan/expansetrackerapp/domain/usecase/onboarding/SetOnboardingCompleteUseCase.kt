package com.korugan.expansetrackerapp.domain.usecase.onboarding

import com.korugan.expansetrackerapp.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject

class SetOnboardingCompleteUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    suspend operator fun invoke(isComplete: Boolean) {
        repository.setOnBoardingComplete(isComplete)
    }
}