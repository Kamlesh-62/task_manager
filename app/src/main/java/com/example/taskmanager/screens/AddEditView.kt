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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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

@Composable

fun AddEditView(
    id: Long,
    viewModel: TasksViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            CustomIndividualTopBar(
                title =
                    if (id != 0L)
                        stringResource(id = R.string.update_task)
                    else
                        stringResource(id = R.string.add_edit_task)
            ){
                navController.navigateUp()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Title",
                value = viewModel.taskTitleState,
                onValueChanged = { it ->
                    viewModel.onValueTitleChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Description",
                value = viewModel.taskDescription,
                onValueChanged = { it ->
                    viewModel.onValueDescriptionChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if(viewModel.taskDescription.isNotEmpty() && viewModel.taskTitleState.isNotEmpty()){

                }
            }) {
                Text(
                    text = if (id != 0L)
                        stringResource(id = R.string.add_edit_task)
                    else stringResource(id = R.string.update_task)
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
        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.app_bar_color),
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Black
        )
    )
}
