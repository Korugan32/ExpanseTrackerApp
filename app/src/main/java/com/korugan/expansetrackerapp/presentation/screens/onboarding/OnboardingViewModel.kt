package com.korugan.expansetrackerapp.presentation.screens.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.domain.usecase.onboarding.IsOnboardingCompleteUseCase
import com.korugan.expansetrackerapp.domain.usecase.onboarding.SetOnboardingCompleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val isOnboardingCompleteUseCase: IsOnboardingCompleteUseCase,
    private val setOnboardingCompleteUseCase: SetOnboardingCompleteUseCase
) : ViewModel() {

    val isOnboardingComplete = MutableLiveData<Boolean>(null)

    init {
        viewModelScope.launch {
            isOnboardingComplete.value = isOnboardingCompleteUseCase()
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            setOnboardingCompleteUseCase(true)
        }
    }
}