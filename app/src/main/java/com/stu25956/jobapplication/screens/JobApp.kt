package com.stu25956.jobapplication.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stu25956.jobapplication.data.Decorator
import com.stu25956.jobapplication.viewmodel.DecoratorViewModel

@Composable
// JobApp is the main entry point for the application
fun JobApp(viewModel: DecoratorViewModel) {
    // NavController to handle navigation
    val navController = rememberNavController()
    // NavHost for the application
    NavHost(navController, startDestination = "search") {
        // Composable for the search screen
        composable("search") {
            SearchScreen(viewModel = viewModel, onSelectDecorator = { decorator ->
                navController.currentBackStackEntry?.savedStateHandle?.set("selectedDecorator", decorator)
                navController.navigate("booking")
            })
        }
        // Composable for the decorator details screen
        composable("booking") {
            val decorator = navController.previousBackStackEntry?.savedStateHandle?.get<Decorator>("selectedDecorator")
            if (decorator != null) {
                DecoratorScreen(decorator = decorator, onBack = { navController.popBackStack() })
            } else {
                // Handle case when decorator is null
                navController.currentBackStackEntry
            }
        }
    }
}


