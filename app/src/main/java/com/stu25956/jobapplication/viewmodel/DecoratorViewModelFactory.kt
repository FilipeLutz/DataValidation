package com.stu25956.jobapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// ViewModelFactory for DecoratorViewModel
@Suppress("UNCHECKED_CAST")
// DecoratorViewModelFactory class
class DecoratorViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    // Create method
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Return DecoratorViewModel instance
        return when {
            modelClass.isAssignableFrom(DecoratorViewModel::class.java) -> {
                DecoratorViewModel(application) as T
            }
            // Throw IllegalArgumentException if ViewModel class is unknown
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}