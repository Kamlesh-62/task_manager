package com.example.taskmanager.screens

import android.R.attr.label
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.Screen
import com.example.taskmanager.data.TasksViewModel
import com.example.taskmanager.components.CustomIndividualTopBar
import com.example.taskmanager.data.Task
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    id: Long,
    viewModel: TasksViewModel,
    navController: NavController
) {
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    if(id != 0L){
        val task = viewModel.getATaskById(id).collectAsState(initial = Task(
            id = 0L,
            title = "",
            description = ""
        ))
        viewModel.taskTitleState = task.value.title
        viewModel.taskDescription = task.value.description
    }else{
        viewModel.taskTitleState = ""
        viewModel.taskDescription = ""
    }

    Scaffold(
        topBar = {
            CustomIndividualTopBar(
                title = if (id != 0L)
                    stringResource(id = R.string.update_task)
                else
                    stringResource(id = R.string.add_edit_task)
            ) {
                navController.navigateUp()
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Title",
                value = viewModel.taskTitleState,
                onValueChanged = { viewModel.onValueTitleChange(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Description",
                value = viewModel.taskDescription,
                onValueChanged = { viewModel.onValueDescriptionChange(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                when {
                    viewModel.taskTitleState.isEmpty() || viewModel.taskDescription.isEmpty() -> {
                        snackMessage.value = "Please fill in all fields."
                    }
                    id != 0L -> {
                        viewModel.updateTask(
                            Task(
                                id = id,
                                title = viewModel.taskTitleState.trim(),
                                description = viewModel.taskDescription.trim()
                            )
                        )
                        snackMessage.value = "Task updated."
                    }
                    else -> {
                        viewModel.addTask(
                            Task(
                                title = viewModel.taskTitleState.trim(),
                                description = viewModel.taskDescription.trim()
                            )
                        )
                        snackMessage.value = "New task created."
                    }
                }

                scope.launch {
                    snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
            }) {
                Text(
                    text = if (id != 0L)
                        stringResource(id = R.string.update_task)
                    else
                        stringResource(id = R.string.add_edit_task)
                )
            }
        }
    }
}


@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.app_bar_color),
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Black
        )
    )
}
