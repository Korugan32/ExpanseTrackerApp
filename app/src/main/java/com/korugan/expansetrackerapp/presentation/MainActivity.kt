package com.korugan.expansetrackerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.korugan.expansetrackerapp.presentation.common.components.BottomBarDesign
import com.korugan.expansetrackerapp.presentation.screens.onboarding.OnBoardingScreen

import com.korugan.expansetrackerapp.presentation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                OnBoardingScreen()
            }
        }
    }
}