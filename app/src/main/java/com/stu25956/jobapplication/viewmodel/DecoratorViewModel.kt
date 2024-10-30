package com.stu25956.jobapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.stu25956.jobapplication.data.AppDatabase
import com.stu25956.jobapplication.data.Decorator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DecoratorViewModel(application: Application) : AndroidViewModel(application) {
    // Access the DAO
    private val decoratorDao = AppDatabase.getDatabase(application).decoratorDao()
    // StateFlow to hold the list of decorators
    private val _availableDecorators = MutableStateFlow<List<Decorator>>(emptyList())
    val availableDecorators = _availableDecorators.asStateFlow()
    // StateFlow to hold the search message
    fun searchDecorators(location: String, fromDate: String, toDate: String) {
        viewModelScope.launch {
            val convertedFromDate = convertDateFormat(fromDate)
            val convertedToDate = convertDateFormat(toDate)
            // Search for decorators in the given location and date range
            Log.d("DecoratorViewModel", "Searching for decorators in $location from $convertedFromDate to $convertedToDate")
            val decorators = decoratorDao.searchAvailableDecorators(location, convertedFromDate, convertedToDate)
            // Log the number of decorators found
            Log.d("DecoratorViewModel", "Found ${decorators.size} decorators")

            // Update state with unique decorators
            _availableDecorators.value = decorators.distinct()
        }
    }
    // Mock data to the database
    fun addMockData(decorators: List<Decorator>) {
        viewModelScope.launch {
            decorators.forEach { decoratorDao.insertDecorator(it) }
        }
    }
    // Fetch all decorators from the database
    fun getAllDecorators() {
        viewModelScope.launch {
            // Fetching all decorators and ensuring uniqueness
            val decorators = decoratorDao.getAllDecorators()
            // Update state with unique decorators
            _availableDecorators.value = decorators.distinct()
        }
    }
    // Convert date format from yyyy-MM-dd to dd-MM-yyyy
    private fun convertDateFormat(date: String): String {
        return try {
            val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val parsedDate: Date = inputFormat.parse(date) ?: return ""
            outputFormat.format(parsedDate)
        } catch (e: Exception) {
            "" // Return empty if parsing fails
        }
    }
}