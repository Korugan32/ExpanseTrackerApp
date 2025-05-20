package com.korugan.expansetrackerapp.presentation.common.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.korugan.expansetrackerapp.presentation.screens.subscription.SubscriptionScreen
import com.korugan.expansetrackerapp.presentation.screens.adding_goal.AddingGoalScreen
import com.korugan.expansetrackerapp.presentation.screens.adding.AddingScreen
import com.korugan.expansetrackerapp.presentation.screens.adding_subscription.AddingSubscriptionScreen
import com.korugan.expansetrackerapp.presentation.screens.adding_transaction.AddingTransactionScreen
import com.korugan.expansetrackerapp.presentation.screens.stock.StockScreen
import com.korugan.expansetrackerapp.presentation.screens.crypto.CryptoScreen
import com.korugan.expansetrackerapp.presentation.screens.crypto_details.CryptoDetailsScreen
import com.korugan.expansetrackerapp.presentation.screens.stock_details.StockDetailsScreen
import com.korugan.expansetrackerapp.presentation.screens.goals.GoalsScreen
import com.korugan.expansetrackerapp.presentation.screens.goals_details.GoalsDetailsScreen
import com.korugan.expansetrackerapp.presentation.screens.main.MainScreen
import com.korugan.expansetrackerapp.presentation.screens.news.NewsScreen
import com.korugan.expansetrackerapp.presentation.screens.onboarding.OnBoardingScreen
import com.korugan.expansetrackerapp.presentation.screens.settings.SettingsScreen
import com.korugan.expansetrackerapp.presentation.screens.transactions.TransactionScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(isOnboardingComplete: Boolean?) {
    val navController = rememberNavController()

    Box(Modifier.background(MaterialTheme.colorScheme.background)) {
        AnimatedNavHost(
            navController = navController,
            startDestination = if (isOnboardingComplete == true) "home" else "onboard",
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() },
            popEnterTransition = { defaultPopEnterTransition() },
            popExitTransition = { defaultPopExitTransition() }
        ) {
            composable(route = "home") {
                MainScreen(navController)
            }
            composable(route = "financial") {
                AddingScreen(navController)
            }
            composable(route = "assets") {
                StockScreen(navController = navController)
            }
            composable(route = "settings") {
                SettingsScreen(navController)
            }
            composable(route = "goals") {
                GoalsScreen(navController)
            }
            composable(route = "onboard") {
                OnBoardingScreen(navHostController = navController)
            }
            composable(route = "news") {
                NewsScreen(navController)
            }
            composable(route = "crypto") {
                CryptoScreen(navController)
            }
            composable(
                route = "cryptoDetails" + "?id={id}&symbol={symbol}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                    },
                    navArgument("symbol") {
                        type = NavType.StringType
                    }
                )
            ) {
                val id = it.arguments?.getString("id")
                val symbol = it.arguments?.getString("symbol")
                CryptoDetailsScreen(navController, id!!, symbol = symbol!!)
            }
            composable(
                route = "stockDetails" + "?symbol={symbol}",
                arguments = listOf(
                    navArgument("symbol") {
                        type = NavType.StringType
                    }
                )
            ) {
                val symbol = it.arguments?.getString("symbol")
                StockDetailsScreen(navController, symbol = symbol)
            }
            composable(route = "addGoal") {
                AddingGoalScreen(navController)
            }
            composable(
                route = "goalDetails/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )) {
                GoalsDetailsScreen(navController)
            }
            composable(route = "addSubscription") {
                AddingSubscriptionScreen(navController)
            }
            composable(route = "addTransactions") {
                AddingTransactionScreen(navController)
            }
            composable(route = "subscription") {
                SubscriptionScreen(navController)
            }
            composable(route = "transactions") {
                TransactionScreen(navController)
            }
        }
    }
}


/* Animations */
private fun defaultEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { 1000 },
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearOutSlowInEasing
        )
    ) + fadeIn(animationSpec = tween(durationMillis = 400))
}

private fun defaultExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -1000 },
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(durationMillis = 400))
}

private fun defaultPopEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -1000 },
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearOutSlowInEasing
        )
    ) + fadeIn(animationSpec = tween(durationMillis = 400))
}

private fun defaultPopExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { 1000 },
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(durationMillis = 400))
}