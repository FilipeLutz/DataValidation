package com.stu25956.jobapplication.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stu25956.jobapplication.data.Decorator
import com.stu25956.jobapplication.viewmodel.DecoratorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: DecoratorViewModel, onSelectDecorator: (Decorator) -> Unit) {
    // State variables to hold user input and button click states
    var location by remember { mutableStateOf("") }
    var dateFrom by remember { mutableStateOf("") }
    var dateTo by remember { mutableStateOf("") }
    var isSearchClicked by remember { mutableStateOf(false) }
    var isAllDecoratorsClicked by remember { mutableStateOf(false) }

    // Collecting available decorators from the ViewModel
    val availableDecorators by viewModel.availableDecorators.collectAsState()

    // Scaffold for the layout
    Scaffold(
        topBar = {
            // TopAppBar for the search screen
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                ),
                title = {
                    Text(
                        "Decorator Search",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
            )
        },
        content = { paddingValues ->
            // Column for the search screen
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(paddingValues)
            ) {
                // OutlinedTextField for location input
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    modifier = Modifier.fillMaxWidth()
                )
                // OutlinedTextField for dateFrom input
                OutlinedTextField(
                    value = dateFrom,
                    onValueChange = { dateFrom = it },
                    label = { Text("Date From (dd-MM-yyyy)") },
                    modifier = Modifier.fillMaxWidth()
                )
                // OutlinedTextField for dateTo input
                OutlinedTextField(
                    value = dateTo,
                    onValueChange = { dateTo = it },
                    label = { Text("Date To (dd-MM-yyyy)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Row for buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Button to search decorators based on input
                    Button(
                        onClick = {
                            Log.d("SearchScreen", "Searching for decorators with: Location: $location, From: $dateFrom, To: $dateTo")
                            viewModel.searchDecorators(location, dateFrom, dateTo)
                            isSearchClicked = true
                            isAllDecoratorsClicked = false
                        },
                        enabled = location.isNotBlank() && dateFrom.isNotBlank() && dateTo.isNotBlank(),
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .width(170.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            "Search",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }

                    // Button to show all decorators
                    Button(
                        onClick = {
                            viewModel.getAllDecorators()
                            isAllDecoratorsClicked = true
                            isSearchClicked = false
                        },
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .width(170.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            "All Decorators",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Display decorators based on search or all decorators button click
                if (isSearchClicked || isAllDecoratorsClicked) {
                    if (availableDecorators.isEmpty()) {
                        // Display message if no decorators are found
                        Text(
                            "No decorators found. Please search again.",
                            color = Color.Red,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(18.dp)
                        )
                    } else {
                        // Display available decorators
                        Row (
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                "Available Decorators",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        // LazyColumn to display decorators
                        LazyColumn {
                            items(availableDecorators) { decorator ->
                                DecoratorItem(decorator, onSelectDecorator)
                            }
                        }
                    }
                }
            }
        }
    )
}