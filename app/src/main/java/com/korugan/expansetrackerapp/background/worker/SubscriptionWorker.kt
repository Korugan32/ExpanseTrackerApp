package com.korugan.expansetrackerapp.background.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.korugan.expansetrackerapp.domain.usecase.subscription.CheckAndAddRecurring
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SubscriptionWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val checkAndAddRecurring: CheckAndAddRecurring
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("WorkerD", "doWork called")
        checkAndAddRecurring()
        return Result.success()
    }
}