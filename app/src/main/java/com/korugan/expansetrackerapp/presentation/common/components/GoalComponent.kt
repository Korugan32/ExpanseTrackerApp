package com.korugan.expansetrackerapp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoalComponent(){
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Yellow)
                .fillMaxWidth()
                .fillMaxHeight(0.12f)
                .padding(8.dp)
            ,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(60.dp)
                        .fillMaxHeight(0.7f)
                        .align(Alignment.CenterVertically)
                        .background(Color.DarkGray)
                        .padding(8.dp),
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.goals),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterVertically)

                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)

                )
                {
                    Text(
                        text = "Play Station",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = 0.75f,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        trackColor = Color.LightGray
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Saved $2,200 / 80%",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = textBlue
                        )
                        Text(
                            text = "Total : $3.300",
                            fontSize = 15.sp
                        )


                    }
                }

            }


        }
    }
}