package com.korugan.expansetrackerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.korugan.expansetrackerapp.presentation.common.navigation.Navigation
import com.korugan.expansetrackerapp.presentation.ui.theme.ExpanseTrackerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpanseTrackerAppTheme {
                Navigation()
            }
        }
    }
}