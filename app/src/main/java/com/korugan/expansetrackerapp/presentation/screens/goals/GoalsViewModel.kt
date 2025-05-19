package com.korugan.expansetrackerapp.presentation.screens.goals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.usecase.goals.GetAllGoalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val getAllGoalsUseCase: GetAllGoalsUseCase,
) : ViewModel(){

    init {
        getGoals()
    }

    private val _allGoals = MutableStateFlow<List<Goals>>(emptyList())
    val allGoals : StateFlow<List<Goals>> = _allGoals

    private fun getGoals(){
        viewModelScope.launch {
            getAllGoalsUseCase.invoke().collect{
                _allGoals.value = it
            }
        }
    }
}