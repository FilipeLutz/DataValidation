package com.stu25956.jobapplication.data

import androidx.room.*

@Dao
interface DecoratorDao {

    // Insert a new decorator
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDecorator(decorator: Decorator)

    // Retrieve decorators available within a date range and location
    @Query("SELECT * FROM decorators WHERE location = :location AND availableFrom = :fromDate AND availableTo = :toDate")
    suspend fun searchAvailableDecorators(location: String, fromDate: String, toDate: String): List<Decorator>

    // Retrieve all decorators
    @Query("SELECT * FROM decorators")
    suspend fun getAllDecorators(): List<Decorator>

    // Delete a decorator by ID
    @Delete
    suspend fun deleteDecorator(decorator: Decorator)

    // Alternatively, delete by a specific condition
    @Query("DELETE FROM decorators WHERE id = :decoratorId")
    suspend fun deleteDecoratorById(decoratorId: Long)
}
