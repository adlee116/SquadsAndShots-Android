package com.example.squadsandshots_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.squadsandshots_android.presentation.reusableComponents.LoginPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPage {
                Toast.makeText(this, viewModel.add(1,1).toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}




