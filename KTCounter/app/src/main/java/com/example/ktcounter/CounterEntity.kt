package com.example.ktcounter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class CounterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val count: Int = 0
)



