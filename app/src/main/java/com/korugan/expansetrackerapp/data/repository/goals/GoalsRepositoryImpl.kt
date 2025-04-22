package com.korugan.expansetrackerapp.data.repository.goals

import com.korugan.expansetrackerapp.data.local.goals.dao.GoalsDao
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import java.util.Date
import javax.inject.Inject

class GoalsRepositoryImpl @Inject constructor(private val goalsDao: GoalsDao) : GoalsRepository {
    override fun getAllGoals(): List<Goals> {
        return goalsDao.getAllGoals()
    }

    override suspend fun insertGoal(goal: Goals) {
        return goalsDao.insertGoal(goal)
    }

    override suspend fun deleteGoal(goal: Goals) {
        return goalsDao.deleteGoal(goal)
    }

    override suspend fun updateGoal(goal: Goals) {
        return goalsDao.updateGoal(goal)
    }

    override suspend fun getGoalsByStatus(status: String): List<Goals> {
        return goalsDao.getGoalsByStatus(status)
    }

    override suspend fun getOverdueGoals(currentDate: Date): List<Goals> {
        return goalsDao.getOverdueGoals(currentDate)
    }
}