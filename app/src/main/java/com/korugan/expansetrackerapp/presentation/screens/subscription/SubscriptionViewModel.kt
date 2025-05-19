package com.korugan.expansetrackerapp.presentation.screens.subscription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.subscription.entity.Subscription
import com.korugan.expansetrackerapp.domain.usecase.subscription.DeleteSubscriptionUseCase
import com.korugan.expansetrackerapp.domain.usecase.subscription.GetAllSubscriptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val getAllSubscriptionUseCase: GetAllSubscriptionUseCase,
    private val deleteSubscriptionUseCase: DeleteSubscriptionUseCase
): ViewModel(){

    init {
        getSubscription()
    }

    private val _allSubs = MutableStateFlow<List<Subscription>>(emptyList())
    val allSubs: StateFlow<List<Subscription>> = _allSubs

    private fun getSubscription(){
        viewModelScope.launch {
            getAllSubscriptionUseCase.invoke().collect{
                _allSubs.value = it
            }
        }
    }

    fun deleteSubscription(id : Int){
        viewModelScope.launch {
            deleteSubscriptionUseCase.invoke(id)
        }
    }
}