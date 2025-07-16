package com.example.taskmanager

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskmanager.Screen.*
import com.example.taskmanager.data.TasksViewModel
import com.example.taskmanager.screens.AddEditView
import com.example.taskmanager.screens.HomeView
import java.util.Map.entry

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
        composable(
            AddEditScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )) { entry ->
            val id = if (entry.arguments != null) entry?.arguments!!.getLong("id") else 0L
            AddEditView(id = id, viewModel = viewModel, navController = navController)
        }
    }
}