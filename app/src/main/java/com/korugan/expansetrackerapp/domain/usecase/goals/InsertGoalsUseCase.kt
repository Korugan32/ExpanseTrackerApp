package com.korugan.expansetrackerapp.domain.usecase.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import javax.inject.Inject

class InsertGoalsUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
){
    suspend operator fun invoke(goal : Goals) {
        goalsRepository.insertGoal(goal)
    }
}