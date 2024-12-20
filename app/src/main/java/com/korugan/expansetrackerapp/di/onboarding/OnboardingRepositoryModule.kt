package com.korugan.expansetrackerapp.di.onboarding
import com.korugan.expansetrackerapp.data.repository.onboarding.OnboardingRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.onboarding.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository
}