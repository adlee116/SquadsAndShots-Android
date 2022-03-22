package com.squadsandshots_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.squadsandshots_android.presentation.login.LoginPage
import com.squadsandshots_android.presentation.login.SignUpPage

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) { LoginPage() }
        composable(route = Screen.Create.route) { SignUpPage() }
    }
}