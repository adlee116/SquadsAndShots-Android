package com.squadsandshots_android.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.squadsandshots_android.presentation.login.LoginPage
import com.squadsandshots_android.presentation.login.LoginViewModel
import com.squadsandshots_android.presentation.login.SignUpPage
import com.squadsandshots_android.presentation.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                val navController: NavHostController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Login.route) {
                    composable(route = Screen.Login.route) { LoginPage() }
                    composable(route = Screen.Create.route) { SignUpPage() }
                }
            }
        }
    }

}





