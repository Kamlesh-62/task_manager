package com.example.taskmanager

import android.content.Context
import androidx.room.Room
import com.example.taskmanager.data.TaskDataBase
import com.example.taskmanager.data.TaskRepository

object Graph {
    lateinit var dataBase: TaskDataBase

    val taskRepository by lazy{
        TaskRepository(taskManagerDao = dataBase.taskManagerDao())
    }

    fun provide(context: Context){
        dataBase = Room.databaseBuilder(context, TaskDataBase::class.java, "taskmanager.db").build()
    }
}