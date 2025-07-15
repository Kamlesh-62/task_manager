package com.example.taskmanager.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

abstract class TaksManagerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addTask(taskEntity: Task)

    @Query("SELECT * FROM `Task-manager-table`")
    abstract fun getAllTasks(): Flow<List<Task>>

    @Update
    abstract suspend fun updateTask(taskEntity: Task)

    @Delete
    abstract suspend fun delTask(taskEntity: Task)

    @Query("SELECT * FROM `Task-manager-table` WHERE id =:id")
    abstract fun getTask(id:Long): Flow<Task>

}