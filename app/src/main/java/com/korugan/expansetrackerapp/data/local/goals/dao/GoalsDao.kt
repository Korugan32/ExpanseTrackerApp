package com.korugan.expansetrackerapp.data.local.goals.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface GoalsDao {
    @Insert
    suspend fun insertGoal(goal: Goals)

    @Update
    suspend fun updateGoal(goal: Goals)

    @Delete
    suspend fun deleteGoal(goal: Goals)

    @Query("SELECT * FROM goals")
    fun getAllGoals(): Flow<List<Goals>>

    @Query("SELECT * FROM goals WHERE status = :status")
    fun getGoalsByStatus(status: String): Flow<List<Goals>>

    @Query("SELECT * FROM goals WHERE deadline < :currentDate")
    fun getOverdueGoals(currentDate: Date): Flow<List<Goals>>
}