package com.example.ktcounter

//package com.example.ktcounter

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CounterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao
}