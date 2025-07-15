package com.example.taskmanager

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.Screen.*
import com.example.taskmanager.data.TasksViewModel
import com.example.taskmanager.screens.AddEditView
import com.example.taskmanager.screens.HomeView

@Composable
fun Navigation(
    viewModel: TasksViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route
    ) {
        composable(HomeScreen.route) {
            HomeView(navController = navController, viewModel)
        }
        composable(AddEditScreen.route) {
            AddEditView(id = 0L, viewModel = viewModel, navController = navController)
        }
    }
}