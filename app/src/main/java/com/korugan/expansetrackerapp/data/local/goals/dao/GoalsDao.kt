package com.korugan.expansetrackerapp.data.local.goals.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface GoalsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goals)

    @Update
    suspend fun updateGoal(goal: Goals)

    @Query("DELETE FROM Goals WHERE id = :id")
    suspend fun deleteGoal(id: Int)

    @Query("SELECT * FROM Goals")
    fun getAllGoals(): Flow<List<Goals>>

    @Query("SELECT * FROM Goals WHERE status = :status")
    fun getGoalsByStatus(status: String): Flow<List<Goals>>

    @Query("SELECT * FROM Goals WHERE deadline < :currentDate")
    fun getOverdueGoals(currentDate: Date): Flow<List<Goals>>

    @Query("SELECT * FROM Goals WHERE id = :id")
    fun getGoalById(id: Int): Flow<Goals?>
}