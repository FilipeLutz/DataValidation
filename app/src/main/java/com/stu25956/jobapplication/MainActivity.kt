package com.stu25956.jobapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.stu25956.jobapplication.data.Decorator
import com.stu25956.jobapplication.screens.JobApp
import com.stu25956.jobapplication.viewmodel.DecoratorViewModel
import com.stu25956.jobapplication.viewmodel.DecoratorViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: DecoratorViewModel
    // onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(
            this,
            DecoratorViewModelFactory(application)
        )[DecoratorViewModel::class.java]

        // Add mock data to the ViewModel
        val mockDecorators = listOf(
            Decorator(name = "John Smith", location = "Dublin", contactInfo = "johnsmith@example.com", availableFrom = "25-10-2024", availableTo = "10-11-2024"),
            Decorator(name = "Dan Morley", location = "Cork", contactInfo = "danmorley@example.com", availableFrom = "01-11-2024", availableTo = "20-11-2024"),
            Decorator(name = "Filipe Lutz", location = "Torres - RS, Brazil", contactInfo = "filipelutz@example.com", availableFrom = "15-11-2024", availableTo = "05-12-2024"),
            Decorator(name = "Rebecca", location = "Dublin", contactInfo = "rebecca@example.com", availableFrom = "25-11-2024", availableTo = "15-12-2024"),
            Decorator(name = "Alice Johnson", location = "Galway", contactInfo = "alice.johnson@example.com", availableFrom = "30-10-2024", availableTo = "15-11-2024"),
            Decorator(name = "Mark Thompson", location = "Limerick", contactInfo = "mark.thompson@example.com", availableFrom = "05-11-2024", availableTo = "25-11-2024"),
            Decorator(name = "Sophie Turner", location = "Belfast", contactInfo = "sophie.turner@example.com", availableFrom = "10-11-2024", availableTo = "01-12-2024"),
            Decorator(name = "Chris Evans", location = "Dublin", contactInfo = "chris.evans@example.com", availableFrom = "28-10-2024", availableTo = "18-11-2024"),
            Decorator(name = "Liam Neeson", location = "Waterford", contactInfo = "liam.neeson@example.com", availableFrom = "12-11-2024", availableTo = "30-11-2024"),
            Decorator(name = "Emma Watson", location = "Dublin", contactInfo = "emma.watson@example.com", availableFrom = "20-11-2024", availableTo = "10-12-2024"),
            Decorator(name = "David Beckham", location = "Cork", contactInfo = "david.beckham@example.com", availableFrom = "01-11-2024", availableTo = "15-11-2024"),
            Decorator(name = "Olivia Brown", location = "Derry", contactInfo = "olivia.brown@example.com", availableFrom = "25-11-2024", availableTo = "20-12-2024"),
            Decorator(name = "Tom Cruise", location = "Dublin", contactInfo = "tomcruise@example.com", availableFrom = "05-11-2024", availableTo = "25-11-2024")
        )
        // Add mock data to the ViewModel
        viewModel.addMockData(mockDecorators)

        // Set the Compose content with JobApp and pass the initialized ViewModel
        setContent {
            JobApp(viewModel = viewModel)
        }
    }
}