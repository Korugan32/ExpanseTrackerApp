package com.korugan.expansetrackerapp.domain.usecase.subscription

import android.util.Log
import com.korugan.expansetrackerapp.data.local.financial_transactions.entity.Financials
import com.korugan.expansetrackerapp.domain.repository.financial.FinancialRepository
import com.korugan.expansetrackerapp.domain.repository.subscription.SubscriptionRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

class CheckAndAddRecurring @Inject constructor(
    private val repo: SubscriptionRepository,
    private val financialsRepo: FinancialRepository,
    private val subscriptionRepository: SubscriptionRepository
) {
    suspend operator fun invoke() {
        try {
            val today = LocalDate.now().dayOfMonth
            val subscriptions = repo.getAllSubscriptions().first()
            subscriptions.filter { it.dayOfMonth == today && !it.isAdded }.forEach { sub ->
                Log.d("CheckAndAdd", "Adding ${sub.title} for today ($today)")
                financialsRepo.insertFinancial(
                    Financials(
                        amount = sub.amount,
                        name = sub.title,
                        description = "Adding By Automatically",
                        createDate = Date(),
                    )
                )
                subscriptionRepository.updateSubscription(
                    sub.copy(isAdded = true)
                )
            }
        } catch (e: Exception) {
            Log.e("CheckAndAdd", "Error occurred: ${e.message}", e)
        }
    }
}