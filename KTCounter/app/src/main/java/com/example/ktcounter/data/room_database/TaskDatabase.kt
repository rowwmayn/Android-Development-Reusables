package com.example.ktcounter.data.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
/*
@Database(entities = [TaskItem::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    // So that only one database exists at a single time
    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE  ?: Synchronized(lock=this) {

            }
        }
    }

}
*/
