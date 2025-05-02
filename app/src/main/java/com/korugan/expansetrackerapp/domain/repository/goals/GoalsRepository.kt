package com.korugan.expansetrackerapp.domain.repository.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface GoalsRepository {
    fun getAllGoals() : Flow<List<Goals>>
    suspend fun insertGoal(goal: Goals)
    suspend fun deleteGoal(goal: Goals)
    suspend fun updateGoal(goal: Goals)
    suspend fun getGoalsByStatus(status: String): Flow<List<Goals>>
    suspend fun getOverdueGoals(currentDate: Date): Flow<List<Goals>>
}