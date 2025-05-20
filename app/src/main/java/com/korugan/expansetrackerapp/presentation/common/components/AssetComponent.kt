package com.korugan.expansetrackerapp.presentation.common.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korugan.expansetrackerapp.R
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import java.text.SimpleDateFormat

@SuppressLint("DefaultLocale", "SimpleDateFormat")
@Composable
fun AssetComponent(assets: Assets, currentAssetPrice: Double?, onClick: () -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 140.dp else 70.dp,
        label = "ExpandAnimation"
    )
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "RotationAnimation"
    )

    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable { expanded = !expanded }
            .height(cardHeight)
            .padding(10.dp),
    ) {
        if (currentAssetPrice != null) {
            val dif = currentAssetPrice - assets.purchasePrice
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painterResource(
                            if (dif > 0) R.drawable.ic_transaction
                            else if (dif == 0.0) R.drawable.ic_transaction_s
                            else R.drawable.ic_transaction_negative
                        ),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Column {
                        Text(assets.name, fontSize = 18.sp)
                        Text(String.format("$%.2f", assets.purchasePrice), color = Color.Gray)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(
                        modifier = Modifier.width(80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val percentage = ((dif) / assets.purchasePrice) * 100
                        Row(
                            Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.background),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "%" + String.format("%.2f", percentage),
                                color = if (percentage > 0) MaterialTheme.colorScheme.surface else if (percentage == 0.0) Color.Gray else MaterialTheme.colorScheme.inverseSurface
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(
                            fontSize = 14.sp,
                            text = String.format("$%.2f", dif),
                            color = if (dif > 0) MaterialTheme.colorScheme.surface else if (dif == 0.0) Color.Gray else MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand Arrow",
                        modifier = Modifier
                            .size(14.dp)
                            .rotate(rotation),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            if (expanded) {
                Text(
                    "Total Worth : " + String.format(
                        "$%.2f",
                        (currentAssetPrice * assets.amount)
                    )
                )

                Text("Description :  ${assets.description}")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Date : " + SimpleDateFormat("dd.MM.yy").format(assets.createDate))
                    IconButton(
                        onClick =  onClick ,
                        content = {
                            Icon(
                                Icons.Default.Delete, contentDescription = "DeleteIcon",
                                tint = MaterialTheme.colorScheme.inverseSurface,
                            )
                        }
                    )
                }
            }
        } else {
            CircularProgressIndicator(Modifier.size(24.dp))
        }
        Spacer(Modifier.height(8.dp))
    }
}

