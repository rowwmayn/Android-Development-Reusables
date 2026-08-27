package com.example.ktcounter

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CounterDao{
    @Upsert
    suspend fun upsertCounter(counter: CounterEntity)

    @Query("SELECT * FROM counter WHERE id=1")
    suspend fun getCounter(): CounterEntity?

}

