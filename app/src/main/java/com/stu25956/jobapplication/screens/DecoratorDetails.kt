package com.stu25956.jobapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stu25956.jobapplication.data.Decorator

@Composable
fun DecoratorScreen(decorator: Decorator, onBack: () -> Unit) {
    // Column for the booking screen
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        // Text for the decorator details
        Text(
            "Decorator Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Card for the decorator details
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            // Column for the decorator details
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    "Name: ${decorator.name}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    "Location: ${decorator.location}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Contact: ${decorator.contactInfo}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Available From: ${decorator.availableFrom}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Available To: ${decorator.availableTo}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        // Row for the button
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            // Button to go back to search screen
            Button(
                onClick = { onBack() },
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(
                    "Back to Search",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}