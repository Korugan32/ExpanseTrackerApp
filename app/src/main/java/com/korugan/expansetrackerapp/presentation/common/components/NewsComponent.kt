package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .size(340.dp)
                .background(color = Color.LightGray),

        ) {
            Image(painter = painterResource(R.drawable.news),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp) // Gölge ve başlık için ayrılan yükseklik
                    .align(Alignment.BottomCenter) // Resmin altına hizalar
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 0f,
                            endY = 100f
                        )
                    )
            )

            Text(text = "Türkiye’yi Yasa Boğan Deprem: Maraş Merkezli Felaket ",
                fontSize = 18.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomStart)
                    .padding(24.dp,),

                )

            Text(text = "CNN Türk •Bugün",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.BottomEnd)
                    .padding(8.dp))






        }

    }


}