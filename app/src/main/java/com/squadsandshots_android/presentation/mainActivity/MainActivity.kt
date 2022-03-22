package com.squadsandshots_android.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.squadsandshots_android.presentation.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController set")
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalNavController provides rememberNavController()) {
                SetupNavGraph(LocalNavController.current)
            }
        }
    }

}





