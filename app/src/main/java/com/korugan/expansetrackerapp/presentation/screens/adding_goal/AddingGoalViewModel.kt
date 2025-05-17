package com.korugan.expansetrackerapp.presentation.screens.adding_goal

import androidx.lifecycle.ViewModel
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.domain.usecase.goals.InsertGoalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class AddingGoalViewModel @Inject constructor(
    private val insertGoalsUseCase: InsertGoalsUseCase
) : ViewModel (){

    fun insertGoal(goal : Goals){
        viewModelScope.launch {
            insertGoalsUseCase.invoke(goal)
        }
    }
}