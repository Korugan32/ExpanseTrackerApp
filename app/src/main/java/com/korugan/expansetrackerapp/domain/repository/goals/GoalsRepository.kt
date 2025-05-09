package com.korugan.expansetrackerapp.domain.repository.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface GoalsRepository {
    fun getAllGoals() : Flow<List<Goals>>
    suspend fun insertGoal(goal: Goals)
    suspend fun deleteGoal(id: Int)
    suspend fun updateGoal(goal: Goals)
    fun getGoalsByStatus(status: String): Flow<List<Goals>>
    fun getOverdueGoals(currentDate: Date): Flow<List<Goals>>
    fun getGoalByID(id : Int) : Flow<Goals>
}