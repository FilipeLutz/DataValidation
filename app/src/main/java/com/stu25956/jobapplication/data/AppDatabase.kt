package com.stu25956.jobapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Database class for Room
@Database(entities = [Decorator::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // DAOs for database
    abstract fun decoratorDao(): DecoratorDao
    // Singleton pattern for database
    companion object {
        @Volatile
        // Singleton instance of database
        private var INSTANCE: AppDatabase? = null
        // Get database instance
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}