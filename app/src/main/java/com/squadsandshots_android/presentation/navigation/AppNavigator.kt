package com.squadsandshots_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppNavigator(navController: NavController = rememberNavController()) = remember { AppNavigator(navController) }

class AppNavigator constructor(
    private val navController: NavController
) {
    fun navigateToLoginPage() {
        navController.navigate("login") {  }
    }
}



