package com.korugan.expansetrackerapp.domain.repository.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import java.util.Date

interface GoalsRepository {
    fun getAllGoals() : List<Goals>
    suspend fun insertGoal(goal: Goals)
    suspend fun deleteGoal(goal: Goals)
    suspend fun updateGoal(goal: Goals)
    suspend fun getGoalsByStatus(status: String): List<Goals>
    suspend fun getOverdueGoals(currentDate: Date): List<Goals>
}