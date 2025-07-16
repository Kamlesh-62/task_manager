package com.example.taskmanager.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.Group
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TasksViewModel(
    private val taskRepository: TaskRepository = Graph.taskRepository
) : ViewModel() {
    var taskTitleState by mutableStateOf("")
    var taskDescription by mutableStateOf("")

    //    this function will change the description value.
    fun onValueDescriptionChange(description: String) {
        taskDescription = description
    }

    //    this function will change the title value.
    fun onValueTitleChange(title: String) {
        taskTitleState = title
    }

    // all database related task
    lateinit var getAllTasks : Flow<List<Task>>

    init {
        viewModelScope.launch {
            getAllTasks = taskRepository.getAllTasks()
        }
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            taskRepository.addATask(task = task)
        }
    }

    fun getATaskById(id:Long): Flow<Task>{
        return taskRepository.getATask(id)
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            taskRepository.updateTask(task = task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(task = task)
        }
    }
}