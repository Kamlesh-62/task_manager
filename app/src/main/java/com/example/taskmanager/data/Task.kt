package com.example.taskmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task-manager-table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "task-title")
    val title: String = "",
    @ColumnInfo(name = "task-desc")
    val description:String = ""
)

object DummyTask{
    val taskList = listOf(
        Task(
            title = "Buy a new iPhone17",
            description = "Apple phone"
        ),Task(
            title = "Buy a new apple watch",
            description = "Apple Watch"
        ),Task(
            title = "Buy a new Airpods",
            description = "Apple ear buds"
        ),
    )
}
