package com.korugan.expansetrackerapp.presentation.screens.goals_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.usecase.goals.DeleteGoalsUseCase
import com.korugan.expansetrackerapp.domain.usecase.goals.GetGoalByIDUseCase
import com.korugan.expansetrackerapp.domain.usecase.goals.UpdateGoalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsDetailsViewModel @Inject constructor(
    private val deleteGoalsUseCase: DeleteGoalsUseCase,
    private val updateGoalsUseCase: UpdateGoalsUseCase,
    private val getGoalByIDUseCase: GetGoalByIDUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady

    init {
        viewModelScope.launch {
            delay(500)
            savedStateHandle.get<Int>("id")?.let { id ->
                getGoalByID(id)
            }
            _isReady.value = true
        }
    }

    fun deleteGoal(id: Int) {
        viewModelScope.launch {
            deleteGoalsUseCase.invoke(id)
            _uiEvent.value = UiEvent.GoalDeleted
        }
    }

    fun updateGoal(goal: Goals) {
        viewModelScope.launch {
            updateGoalsUseCase.invoke(goal)
        }
    }

    private val _uiEvent = MutableStateFlow<UiEvent?>(null)
    val uiEvent: StateFlow<UiEvent?> = _uiEvent

    sealed class UiEvent {
        object GoalDeleted : UiEvent()
    }

    private val _goal = MutableStateFlow<Goals?>(null)
    val goal: StateFlow<Goals?> = _goal

    private fun getGoalByID(id: Int) {
        viewModelScope.launch {
            getGoalByIDUseCase.invoke(id).collect {
                _goal.value = it
            }
        }
    }
}