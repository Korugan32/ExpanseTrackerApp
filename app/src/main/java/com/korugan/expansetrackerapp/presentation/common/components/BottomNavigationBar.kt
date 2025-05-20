package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import blue
import com.korugan.expansetrackerapp.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Row(
        Modifier
            .shadow(10.dp)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            if (currentRoute != "home")
                navController.navigate("home")
        }) {
            Icon(
                painterResource(R.drawable.home),
                contentDescription = null,
                tint = if (currentRoute == "home") blue else MaterialTheme.colorScheme.onBackground,
            )
        }
        IconButton(onClick = {
            if (currentRoute != "news")
                navController.navigate("news")
        }) {
            Icon(
                painter = painterResource(R.drawable.news_svgrepo_com),
                contentDescription = null,
                tint = if (currentRoute == "news") blue else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(30.dp)
            )
        }
        IconButton(onClick = {
            if (currentRoute != "subscription")
                navController.navigate("subscription")
        }) {
            Icon(
                Icons.Outlined.Notifications,
                contentDescription = null,
                tint = if (currentRoute == "subscription") blue else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(30.dp)
            )
        }
        IconButton(onClick = {
            if (currentRoute != "goals")
                navController.navigate("goals")
        }) {
            Icon(
                painter = painterResource(R.drawable.goal),
                contentDescription = null,
                tint = if (currentRoute == "goals") blue else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(30.dp)
            )
        }
        IconButton(onClick = {
            if (currentRoute != "transactions") {
                navController.navigate("transactions")
            }
        }) {
            Icon(
                painter = painterResource(R.drawable.transaction),
                contentDescription = null,
                tint = if (currentRoute == "transactions") blue else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}