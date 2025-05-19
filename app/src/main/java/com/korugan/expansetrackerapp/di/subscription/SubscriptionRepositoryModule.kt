package com.korugan.expansetrackerapp.di.subscription

import com.korugan.expansetrackerapp.data.repository.subscription.SubscriptionRepositoryImpl
import com.korugan.expansetrackerapp.domain.repository.subscription.SubscriptionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class SubscriptionRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSubscriptionRepository(
        subscriptionRepositoryImpl : SubscriptionRepositoryImpl
    ): SubscriptionRepository

}