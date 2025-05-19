package com.korugan.expansetrackerapp.domain.repository.subscription

import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription
import kotlinx.coroutines.flow.Flow

interface SubscriptionRepository {
    fun getAllSubscriptions(): Flow<List<Subscription>>
    suspend fun addSubscription(subscription: Subscription)
    suspend fun deleteSubscription(id: Int)
    suspend fun updateSubscription(subscription: Subscription)
    fun getSubscriptionById(id : Int) : Flow<Subscription>
    suspend fun resetFlags()
}