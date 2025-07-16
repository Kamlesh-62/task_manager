package com.example.taskmanager.data

import kotlinx.coroutines.flow.Flow

class TaskRepository (private val taskManagerDao: TaskManagerDao) {

    suspend fun addATask(task: Task) {
        taskManagerDao.addTask(task)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return taskManagerDao.getAllTasks()
    }

    fun getATask(id:Long) : Flow<Task>{
        return taskManagerDao.getTask(id)
    }

    suspend fun updateTask (task: Task){
        taskManagerDao.updateTask(task)
    }
    suspend fun deleteTask(task: Task){
        taskManagerDao.delTask(task)
    }

}