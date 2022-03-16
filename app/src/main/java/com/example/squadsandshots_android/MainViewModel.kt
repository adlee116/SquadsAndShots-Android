package com.example.squadsandshots_android

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    fun add(number: Int, another: Int): Int {
        return number + another
    }
}