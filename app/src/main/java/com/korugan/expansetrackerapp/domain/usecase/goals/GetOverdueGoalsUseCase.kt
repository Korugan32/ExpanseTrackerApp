package com.korugan.expansetrackerapp.domain.usecase.goals

import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.repository.goals.GoalsRepository
import java.util.Date
import javax.inject.Inject

class GetOverdueGoalsUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    suspend operator fun invoke(currentDate: Date): List<Goals> {
        return goalsRepository.getOverdueGoals(currentDate)
    }
}