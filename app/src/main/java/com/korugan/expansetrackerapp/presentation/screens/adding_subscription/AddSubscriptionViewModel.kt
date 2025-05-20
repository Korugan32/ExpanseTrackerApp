package com.korugan.expansetrackerapp.presentation.screens.adding_subscription

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.korugan.expansetrackerapp.background.worker.SubscriptionWorker
import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription
import com.korugan.expansetrackerapp.domain.usecase.subscription.InsertSubscriptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSubscriptionViewModel @Inject constructor(
private val insertSubscriptionUseCase: InsertSubscriptionUseCase
) : ViewModel() {

    fun insertSub(context : Context,sub : Subscription){
        viewModelScope.launch {
            insertSubscriptionUseCase.invoke(sub)
            val workRequest = OneTimeWorkRequestBuilder<SubscriptionWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }

}