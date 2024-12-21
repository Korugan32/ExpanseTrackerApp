package com.korugan.expansetrackerapp.domain.usecase.onboarding

import com.korugan.expansetrackerapp.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject


class IsOnboardingCompleteUseCase @Inject constructor(private val repository: OnboardingRepository) {
    suspend operator fun invoke(): Boolean {
        return repository.onBoardingComplete()
    }
}
