package com.korugan.expansetrackerapp.presentation.screens.goals_details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import com.korugan.expansetrackerapp.presentation.common.components.BottomNavigationBar
import com.korugan.expansetrackerapp.presentation.common.components.GoalDetailsHeader
import com.korugan.expansetrackerapp.presentation.common.components.TextFieldItem
import com.korugan.expansetrackerapp.presentation.screens.goals_details.GoalsDetailsViewModel.UiEvent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "DefaultLocale")
@Composable
fun GoalsDetailsScreen(
    navController: NavHostController,
    viewModel: GoalsDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isReady = viewModel.isReady.collectAsState()
    val uiEvent by viewModel.uiEvent.collectAsState()
    val goal = viewModel.goal.collectAsState()
    var isEdit by remember { mutableStateOf(false) }
    var transaction by remember { mutableStateOf("") }
    val dataPickerState = rememberDatePickerState()

    LaunchedEffect(uiEvent) {
        if (uiEvent is UiEvent.GoalDeleted) {
            navController.popBackStack()
        }
    }

    if (isReady.value && goal.value != null) {
        val goal = goal.value!!
        var isDatePickerEnable by remember { mutableStateOf(false) }
        var financialName by remember { mutableStateOf(goal.title) }
        var progress by remember { mutableDoubleStateOf(goal.progress.toDouble()) }
        var financialDescription by remember { mutableStateOf(goal.description) }
        var financialPrice by remember { mutableStateOf(goal.price.toString()) }
        var date by remember { mutableStateOf(goal.deadline) }
        Scaffold(
            topBar = {
                GoalDetailsHeader(
                    navController,
                    goal.title.toString(),
                    isEdit,
                    {
                        isEdit = !isEdit
                        if (!isEdit) {
                            viewModel.updateGoal(
                                Goals(
                                    title = financialName,
                                    description = financialDescription,
                                    deadline = date,
                                    progress = ((goal.totalInsert / financialPrice.toDouble()) * 100),
                                    status = if (goal.totalInsert == financialPrice.toDouble()) {
                                        "done"
                                    } else "ongoing",
                                    price = String.format("%.1f", financialPrice.toDouble())
                                        .replace(",",".").toDouble(),
                                    id = goal.id,
                                    totalInsert = goal.totalInsert
                                )
                            )
                            navController.popBackStack()
                        }
                    },
                    {
                        viewModel.deleteGoal(goal.id)
                    }
                )
            },
            bottomBar = { BottomNavigationBar(navController) },
            modifier = Modifier
                .systemBarsPadding()
                .background(MaterialTheme.colorScheme.background)
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
                    onValueChange = { financialName = it },
                    enabled = isEdit
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
                    onValueChange = { financialPrice = it },
                    enabled = isEdit
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
                        Text(
                            formattedDate.toString(),
                            color = if (isEdit) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.6f
                            )
                        )
                        IconButton({ isDatePickerEnable = !isDatePickerEnable }, enabled = isEdit) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                "",
                                tint = if (isEdit) blue else MaterialTheme.colorScheme.onBackground.copy(
                                    0.6f
                                )
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
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        disabledContainerColor = MaterialTheme.colorScheme.background,
                        disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        disabledLabelColor = MaterialTheme.colorScheme.onBackground
                    ),
                    placeholder = {
                        Text(
                            "Description",
                            fontWeight = FontWeight.W500,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    enabled = isEdit
                )
                Spacer(Modifier.padding(5.dp))
                Column(Modifier.padding(5.dp)) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Progress")
                        Text(
                            "$${
                                String.format(
                                    "%.1f",
                                    goal.totalInsert
                                )
                            }/$" + goal.price.toString()
                        )
                    }
                    Spacer(Modifier.padding(5.dp))
                    LinearProgressIndicator(
                        progress = (progress / 100).toFloat(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        backgroundColor = Color.Gray.copy(0.4f),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(Modifier.padding(5.dp))
                Row(Modifier.fillMaxWidth()) {
                    TextField(
                        transaction, { transaction = it },
                        modifier = Modifier
                            .height(55.dp)
                            .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            disabledContainerColor = MaterialTheme.colorScheme.background,
                            disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            disabledLabelColor = MaterialTheme.colorScheme.onBackground
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        placeholder = {
                            Text(
                                "0",
                                fontWeight = FontWeight.W500,
                                fontSize = 20.sp,
                            )
                        },
                    )
                    Button(
                        onClick = {
                            /*.replace(",", ".") for locale differences*/
                            val t = transaction.replace(",", ".").toDouble()
                            if (transaction.isNotEmpty()) {
                                if (t.toDouble() != 0.0) {
                                    if ((t + goal.totalInsert <= goal.price) && (t + goal.totalInsert >= 0)) {
                                        progress = ((t + (goal.price * (goal.progress / 100))) / goal.price) * 100
                                        viewModel.updateGoal(
                                            Goals(
                                                title = goal.title,
                                                description = goal.description,
                                                deadline = goal.deadline,
                                                progress = progress,
                                                status = if (goal.price == t + goal.totalInsert) {
                                                    "done"
                                                } else "ongoing",
                                                price = goal.price,
                                                id = goal.id,
                                                totalInsert = String.format(
                                                    "%.1f",
                                                    t
                                                ).replace(",", ".").toDouble() + goal.totalInsert
                                            )
                                        )
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Transaction exceeds the goal amount or is invalid.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Transaction amount must be different from zero.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please enter a transaction amount.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier.height(55.dp).fillMaxWidth(),
                        shape = RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp)
                    ) {
                        Text("Add", textAlign = TextAlign.Center)
                    }
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
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
