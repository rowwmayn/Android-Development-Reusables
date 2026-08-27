package com.example.ktcounter.data.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val taskName:String,
    val isDone: Boolean = false
)


