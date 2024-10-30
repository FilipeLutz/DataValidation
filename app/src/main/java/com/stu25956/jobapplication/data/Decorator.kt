package com.stu25956.jobapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
// Entity annotation to define the table name
@Entity(tableName = "decorators")
// Decorator data class
data class Decorator(
    // PrimaryKey annotation to define the primary key
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val location: String,
    val contactInfo: String,
    val availableFrom: String,
    val availableTo: String
) : Parcelable