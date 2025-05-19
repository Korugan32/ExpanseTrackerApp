package com.korugan.expansetrackerapp.data.repository.subscription

import com.korugan.expansetrackerapp.data.local.subscription.dao.SubscriptionDao
import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription
import com.korugan.expansetrackerapp.domain.repository.subscription.SubscriptionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SubscriptionRepositoryImpl @Inject constructor(
 private val subscriptionDao: SubscriptionDao
) : SubscriptionRepository{
    override fun getAllSubscriptions(): Flow<List<Subscription>> {
        return subscriptionDao.getAll()
    }

    override suspend fun addSubscription(subscription: Subscription) {
        subscriptionDao.insert(subscription)
    }

    override suspend fun deleteSubscription(id: Int) {
        subscriptionDao.delete(id)
    }

    override suspend fun updateSubscription(subscription: Subscription) {
        subscriptionDao.updateSubscription(subscription)
    }

    override fun getSubscriptionById(id: Int): Flow<Subscription> {
        return subscriptionDao.getSubscriptionById(id)
    }

    override suspend fun resetFlags() {
        subscriptionDao.resetFlags()
    }
}