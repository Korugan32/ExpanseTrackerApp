package com.korugan.expansetrackerapp.domain.usecase.subscription

import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription
import com.korugan.expansetrackerapp.domain.repository.subscription.SubscriptionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSubscriptionUseCase @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository
) {
     operator fun invoke(): Flow<List<Subscription>> {
        return subscriptionRepository.getAllSubscriptions()
    }
}