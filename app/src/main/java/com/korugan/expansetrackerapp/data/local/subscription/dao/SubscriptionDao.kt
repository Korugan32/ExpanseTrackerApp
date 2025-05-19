package com.korugan.expansetrackerapp.data.local.subscription.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionDao {
    @Query("SELECT * FROM subscription")
    fun getAll(): Flow<List<Subscription>>

    @Update
    suspend fun updateSubscription(subscription: Subscription)

    @Insert
    suspend fun insert(subscription: Subscription)

    @Query("DELETE FROM subscription WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM Subscription WHERE id = :id")
    fun getSubscriptionById(id: Int): Flow<Subscription>

    @Query("UPDATE Subscription SET isAdded = 0")
    suspend fun resetFlags()

}