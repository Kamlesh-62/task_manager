package com.example.taskmanager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.Screen
import com.example.taskmanager.components.CustomHomeTopBar
import com.example.taskmanager.data.Task
import com.example.taskmanager.data.TasksViewModel


@Composable
fun HomeView(
    navController: NavController,
    viewModel: TasksViewModel
) {
    Scaffold(
        topBar = {
            CustomHomeTopBar(
                title = "TASK MANAGER"
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(20.dp),
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.app_bar_color),
                onClick = {
                    navController.navigate((Screen.AddEditScreen.route + "/0L"))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        val taskList = viewModel.getAllTasks.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(
                taskList.value,
                key = { task ->
                    task.id
                }
            ) { task ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if (it == SwipeToDismissBoxValue.EndToStart || it == SwipeToDismissBoxValue.StartToEnd) {
                            viewModel.deleteTask(task)
                        }
                        true
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        when(dismissState.dismissDirection) {
                            SwipeToDismissBoxValue.StartToEnd -> {}
                            SwipeToDismissBoxValue.EndToStart -> {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove task",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Gray)
                                        .wrapContentSize(Alignment.CenterEnd)
                                        .padding(12.dp),
                                    tint = Color.White
                                )
                            }
                            SwipeToDismissBoxValue.Settled -> {}
                        }
                    },
                    enableDismissFromEndToStart = true
                ) {
                    TaskItem(
                        task = task,
                        onClick = {
                            val id = task.id
                            navController.navigate(Screen.AddEditScreen.route + "/$id")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = task.title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp
            )
            Text(
                text = task.description,
                fontSize = 15.sp
            )
        }
    }
}