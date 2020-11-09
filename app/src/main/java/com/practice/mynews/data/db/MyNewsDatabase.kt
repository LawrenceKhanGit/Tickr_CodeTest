package com.practice.mynews.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class MyNewsDatabase: RoomDatabase() {
    abstract fun newsDao():MyNewsDao
}