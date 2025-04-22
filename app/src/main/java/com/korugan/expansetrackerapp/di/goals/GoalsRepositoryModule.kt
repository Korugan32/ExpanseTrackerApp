package com.korugan.expansetrackerapp.di.goals

import com.korugan.expansetrackerapp.data.repository.goals.GoalsRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class GoalsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGoalsRepository(
        goalsRepositoryImpl: GoalsRepositoryImpl
    ): GoalsRepository
}