package com.korugan.expansetrackerapp.domain.usecase.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import javax.inject.Inject

class GetAllGoalsUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    operator fun invoke(): List<Goals> {
        return goalsRepository.getAllGoals()
    }
}