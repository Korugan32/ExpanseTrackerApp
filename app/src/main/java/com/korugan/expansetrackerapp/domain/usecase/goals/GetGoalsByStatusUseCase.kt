package com.korugan.expansetrackerapp.domain.usecase.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import javax.inject.Inject

class GetGoalsByStatusUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    suspend operator fun invoke(status: String): List<Goals> {
        return goalsRepository.getGoalsByStatus(status)
    }
}