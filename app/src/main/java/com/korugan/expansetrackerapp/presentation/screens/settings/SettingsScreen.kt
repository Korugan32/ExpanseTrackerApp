package com.korugan.expansetrackerapp.presentation.screens.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.korugan.expansetrackerapp.presentation.common.components.Header
import com.korugan.expansetrackerapp.presentation.common.components.SettingComponent
import com.korugan.expansetrackerapp.util.Constants.ABOUT_TEXT
import com.korugan.expansetrackerapp.util.Constants.DISCLAIMER_TEXT
import com.korugan.expansetrackerapp.util.Constants.PRIVACY_TEXT
import com.korugan.expansetrackerapp.util.Constants.SSS_TEXT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val url = "https://github.com/Korugan32"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    var text by remember { mutableStateOf("") }
    var isOpen by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Header(navController, "Ayarlar")
        Column(Modifier.padding(horizontal = 15.dp)) {
            SettingComponent("Para Birimi") {
                context.startActivity(intent)
            }
            SettingComponent("Sıkça Sorulan Sorular(SSS)") {
                text = SSS_TEXT.trimIndent()
                isOpen = true
            }
            SettingComponent("Arkadaşlarına Öner") {
                context.startActivity(intent)
            }
            SettingComponent("Dil") {
                context.startActivity(intent)
            }
            SettingComponent("Hesap Kitapp Hakkında") {
                text = ABOUT_TEXT.trimIndent()
                isOpen = true
            }
            SettingComponent("Yasal Uyarı") {
                text = DISCLAIMER_TEXT.trimIndent()
                isOpen = true
            }
            SettingComponent("Uygulamayı Puanla") {
                context.startActivity(intent)
            }
            SettingComponent("Kullanıcı Sözleşmesi ve Gizlilik Politikası") {
                text = PRIVACY_TEXT.trimIndent()
                isOpen = true
            }
            SettingComponent("Destek") {
                context.startActivity(intent)
            }
        }
        if (isOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    isOpen = !isOpen
                }, containerColor = MaterialTheme.colorScheme.background
            ) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    item {
                        Text(text)
                    }
                }
            }
        }
    }
}