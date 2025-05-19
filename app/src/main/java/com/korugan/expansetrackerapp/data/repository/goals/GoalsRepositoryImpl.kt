package com.korugan.expansetrackerapp.data.repository.goals

import com.korugan.expansetrackerapp.data.local.goals.dao.GoalsDao
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GoalsRepositoryImpl @Inject constructor(private val goalsDao: GoalsDao) : GoalsRepository {
    override fun getAllGoals(): Flow<List<Goals>> {
        return goalsDao.getAllGoals()
    }

    override suspend fun insertGoal(goal: Goals) {
        return goalsDao.insertGoal(goal)
    }

    override suspend fun deleteGoal(id : Int) {
        goalsDao.deleteGoal(id)
    }

    override suspend fun updateGoal(goal: Goals) {
        return goalsDao.updateGoal(goal)
    }

    override fun getGoalsByStatus(status: String): Flow<List<Goals>>{
        return goalsDao.getGoalsByStatus(status)
    }

    override fun getOverdueGoals(currentDate: Date): Flow<List<Goals>> {
        return goalsDao.getOverdueGoals(currentDate)
    }

    override fun getGoalByID(id: Int): Flow<Goals?> {
        return goalsDao.getGoalById(id)
    }
}