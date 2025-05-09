package com.korugan.expansetrackerapp.domain.usecase.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGoalByIDUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    operator fun invoke(id: Int): Flow<Goals> {
        return goalsRepository.getGoalByID(id)
    }
}