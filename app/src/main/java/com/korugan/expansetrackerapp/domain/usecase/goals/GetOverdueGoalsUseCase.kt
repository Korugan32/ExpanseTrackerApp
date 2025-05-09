package com.korugan.expansetrackerapp.domain.usecase.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetOverdueGoalsUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    operator fun invoke(currentDate: Date): Flow<List<Goals>> {
        return goalsRepository.getOverdueGoals(currentDate)
    }
}