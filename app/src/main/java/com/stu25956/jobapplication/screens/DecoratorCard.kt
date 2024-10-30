package com.stu25956.jobapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stu25956.jobapplication.data.Decorator

@Composable
// DecoratorItem composable function
fun DecoratorItem(decorator: Decorator, onSelectDecorator: (Decorator) -> Unit) {
    // Card composable function
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onSelectDecorator(decorator) }
    ) {
        // Column composable function
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Name: ${decorator.name}",
                style = MaterialTheme.typography.titleLarge
            )
            Text("Location: ${decorator.location}",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Availability: ${decorator.availableFrom} - ${decorator.availableTo}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}