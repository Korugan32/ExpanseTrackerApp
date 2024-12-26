package com.korugan.expansetrackerapp.presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.korugan.expansetrackerapp.R

@Composable
fun OnBoardingScreen(viewModel : OnboardingViewModel = hiltViewModel() , navHostController: NavHostController) {
    val images = listOf(
        R.drawable.first_mage,
        R.drawable.second_mage,
        R.drawable.interest_mortgage_calculator,
    )
    val titles = listOf(
        "Gelir ve Giderinizi Kontrol Ederken Zorlanıyor Musunuz?",
        "Finansal Durumunuzu Görselleştirin",
        "Finansal Hedefler Belirleyin"
    )
    val descriptions = listOf(
        "Bütün harcamalarınızı hızlıca ekleyin ve kolayca finansal durumunuzu anında kontrol edin.",
        "Gelir ve giderlerinizi grafiklerle takip edin. Durumunuzu anında görün.",
        "Kendi hedeflerinizi oluşturun. Adım adım bu hedeflere ulaşın."
    )

    val pagerState = rememberPagerState(
        pageCount = { images.size }
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
        ) { currentPage ->
            Column(
                Modifier
                    .padding(26.dp),
            ) {
                Image(
                    painter = painterResource(images[currentPage]),
                    contentDescription = "",
                )
                Text(
                    text = titles[currentPage],
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp, fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = descriptions[currentPage],
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = 3,
            Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            activeColor = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (pagerState.currentPage == images.size - 1) {
            Button(
                onClick = {
                    viewModel.completeOnboarding()
                    navHostController.navigate("home")},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(36.dp),
            ) {
                Text(
                    text = "Şimdi Başla",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}