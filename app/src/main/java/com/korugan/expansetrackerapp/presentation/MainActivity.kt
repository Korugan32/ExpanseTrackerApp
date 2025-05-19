package com.korugan.expansetrackerapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.korugan.expansetrackerapp.background.worker.ResetSubscriptionFlagsWorker
import com.korugan.expansetrackerapp.background.worker.SubscriptionWorker
import com.korugan.expansetrackerapp.presentation.common.navigation.Navigation
import com.korugan.expansetrackerapp.presentation.screens.onboarding.OnboardingViewModel
import com.korugan.expansetrackerapp.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val resetFlagsWorkRequest = PeriodicWorkRequestBuilder<ResetSubscriptionFlagsWorker>(
            1, TimeUnit.DAYS
        ).setConstraints(
            Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build()
        ).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "reset_subscription_flags_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            resetFlagsWorkRequest
        )

        val dailyWorkRequest = PeriodicWorkRequestBuilder<SubscriptionWorker>(24, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "daily_workerD",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyWorkRequest
        )

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(dailyWorkRequest.id)
            .observe(this) { workInfo ->
                Log.d("WorkerStatus", "State: ${workInfo?.state}")
            }
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val onboardingViewModel: OnboardingViewModel = hiltViewModel()
                val isOnboardingComplete by onboardingViewModel.isOnboardingComplete.observeAsState()
                if (isOnboardingComplete == null) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                    }
                } else {
                    Navigation(isOnboardingComplete!!)
                }
            }
        }
    }
}