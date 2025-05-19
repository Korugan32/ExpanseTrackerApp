package com.korugan.expansetrackerapp.domain.usecase.subscription

import com.korugan.expansetrackerapp.domain.repository.subscription.SubscriptionRepository
import javax.inject.Inject

class DeleteSubscriptionUseCase @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository
) {
    suspend operator fun invoke(id: Int){
        subscriptionRepository.deleteSubscription(id)
    }
}