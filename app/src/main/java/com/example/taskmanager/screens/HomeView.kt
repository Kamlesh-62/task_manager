package com.example.taskmanager.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.R
import com.example.taskmanager.components.AppBarView
import com.example.taskmanager.components.CustomHomeTopBar
import com.example.taskmanager.data.DummyTask
import com.example.taskmanager.data.Task
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.example.taskmanager.Screen
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
                    navController.navigate((Screen.AddEditScreen.route))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(DummyTask.taskList) { task ->
                TaskItem(
                    task = task,
                    onClick = {

                    }
                )
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