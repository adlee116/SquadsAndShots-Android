package com.squadsandshots_android.presentation.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.ramcosta.composedestinations.DestinationsNavHost
import com.squadsandshots_android.presentation.login.NavGraphs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface { DestinationsNavHost(navGraph = NavGraphs.root) }
        }
    }

}





