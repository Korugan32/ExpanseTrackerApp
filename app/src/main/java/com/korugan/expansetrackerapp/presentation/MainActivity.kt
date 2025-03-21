package com.korugan.expansetrackerapp.presentation

import android.os.Bundle
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
import com.korugan.expansetrackerapp.presentation.common.navigation.Navigation
import com.korugan.expansetrackerapp.presentation.screens.onboarding.OnboardingViewModel
import com.korugan.expansetrackerapp.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val onboardingViewModel: OnboardingViewModel = hiltViewModel()
                val isOnboardingComplete by onboardingViewModel.isOnboardingComplete.observeAsState()
                if (isOnboardingComplete == null) {
                    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                    }
                } else {
                    Navigation(isOnboardingComplete!!)
                }
            }
        }
    }
}