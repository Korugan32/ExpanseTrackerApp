package com.korugan.expansetrackerapp.presentation.screens.adding_goal

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import blue
import com.korugan.expansetrackerapp.data.local.goals.entity.Goals
import com.korugan.expansetrackerapp.presentation.common.components.Header
import com.korugan.expansetrackerapp.presentation.common.components.TextFieldItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingGoalScreen(navController: NavHostController, viewModel: AddingGoalViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var isDatePickerEnable by remember { mutableStateOf(false) }
    var financialName by remember { mutableStateOf("") }
    var financialDescription by remember { mutableStateOf("") }
    var financialPrice by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(Date()) }
    val dataPickerState = rememberDatePickerState()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = { Header(navController, "Add Goal") }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(15.dp)
        ) {
            TextFieldItem(
                title = "Title",
                text = financialName,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                placeHolder = {
                    Text(
                        "Title",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                onValueChange = { financialName = it }
            )
            Divider()
            TextFieldItem(
                title = "Price",
                text = financialPrice,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                placeHolder = {
                    Text(
                        "$",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                onValueChange = { financialPrice = it }
            )
            Divider()
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(imageVector = Icons.Default.DateRange, "", tint = blue)
                    Spacer(Modifier.padding(5.dp))
                    Text(
                        "Deadline",
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val date = date
                    val formatter = SimpleDateFormat(
                        "dd.MM.yyyy",
                        Locale.getDefault()
                    )
                    val formattedDate = formatter.format(date)
                    Text(formattedDate.toString())
                    IconButton({ isDatePickerEnable = !isDatePickerEnable }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            "",
                            tint = blue
                        )
                    }
                }
            }
            Divider()
            Spacer(Modifier.padding(5.dp))
            TextField(
                financialDescription,
                onValueChange = { financialDescription = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(15.dp),
                    ),
                textStyle = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        "Description",
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
            )
            Spacer(Modifier.padding(5.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (financialPrice.isNotEmpty() && financialName.isNotEmpty()) {
                            if (financialPrice.toDouble() != 0.0) {
                                viewModel.insertGoal(
                                    Goals(
                                        title = financialName,
                                        description = financialDescription,
                                        deadline = date,
                                        progress = 0.0,
                                        status = "ongoing",
                                        price = financialPrice.toDouble(),
                                        totalInsert = 0.0
                                    )
                                )
                                navController.navigate("home")
                            } else {
                                Toast.makeText(
                                    context,
                                    "Price cannot be 0",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else if (financialName.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Name cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Price cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.width(300.dp)
                ) {
                    Text("Add")
                }
            }
            if (isDatePickerEnable) {
                val selectedDateMillis = dataPickerState.selectedDateMillis
                date = selectedDateMillis?.let { Date(it) } ?: Date()
                ModalBottomSheet(onDismissRequest = {
                    isDatePickerEnable = !isDatePickerEnable
                }, containerColor = MaterialTheme.colorScheme.background) {
                    DatePicker(
                        colors = DatePickerDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.background,
                            dayContentColor = MaterialTheme.colorScheme.onBackground
                        ),
                        state = dataPickerState
                    )
                }
            }
        }
    }
}
