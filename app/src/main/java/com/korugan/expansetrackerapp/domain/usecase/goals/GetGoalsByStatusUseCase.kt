package com.korugan.expansetrackerapp.domain.usecase.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGoalsByStatusUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    operator fun invoke(status: String): Flow<List<Goals>> {
        return goalsRepository.getGoalsByStatus(status)
    }
}