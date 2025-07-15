package com.example.taskmanager

sealed class Screen( val route: String){
    object HomeScreen: Screen("HomeScreen")
    object AddEditScreen:Screen("AddEditScreen")
}