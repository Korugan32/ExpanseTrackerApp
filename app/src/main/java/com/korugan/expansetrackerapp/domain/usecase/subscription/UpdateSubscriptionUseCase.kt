package com.korugan.expansetrackerapp.domain.usecase.subscription

import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription
import com.korugan.expansetrackerapp.domain.repository.subscription.SubscriptionRepository
import javax.inject.Inject

class UpdateSubscriptionUseCase @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository
) {
    suspend operator fun invoke(subscription : Subscription){
        subscriptionRepository.updateSubscription(subscription)
    }
}