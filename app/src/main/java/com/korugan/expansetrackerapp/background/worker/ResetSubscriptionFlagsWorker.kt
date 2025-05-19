package com.korugan.expansetrackerapp.background.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.korugan.expansetrackerapp.domain.repository.subscription.SubscriptionRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ResetSubscriptionFlagsWorker @AssistedInject constructor(
    @Assisted appContext : Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: SubscriptionRepository
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        repository.resetFlags()
        return Result.success()
    }
}