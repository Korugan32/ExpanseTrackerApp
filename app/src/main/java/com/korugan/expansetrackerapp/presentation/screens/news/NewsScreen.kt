package com.korugan.expansetrackerapp.presentation.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.korugan.expansetrackerapp.presentation.common.components.BottomNavigationBar
import com.korugan.expansetrackerapp.presentation.common.components.Header
import com.korugan.expansetrackerapp.presentation.common.components.NewsCard

@Composable
fun NewsScreen(navController: NavHostController, viewModel: NewsViewModel = hiltViewModel()) {
    val newsState = viewModel.stockMarketNewsState.collectAsState()
    Scaffold(
        topBar = { Header(navController, "News") },
        bottomBar = { BottomNavigationBar(navController)},
        modifier = Modifier.systemBarsPadding().background(MaterialTheme.colorScheme.background)
    ) {
        Text(newsState.value.toString())
        LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize().padding(it)) {
            if (newsState.value.stockNews != null) {
                items(newsState.value.stockNews!!.size) { index ->
                    val news = newsState.value.stockNews!![index]
                    NewsCard(title = news.headline, image = news.image, source = news.source, sourceUrl = news.url)
                }
            }
        }
    }
}