package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("loading") { LoadingScreen() }
        composable("new_project") { NewProjectScreen { name, start, end ->
            navController.navigate("project_details/$name/$start/$end")
        }}
        composable("project_details/{name}/{start}/{end}") { backStackEntry ->
            ProjectDetailsScreen(
                projectName = backStackEntry.arguments?.getString("name") ?: "",
                projectDate = backStackEntry.arguments?.getString("start") ?: ""
            )
        }
    }
}
