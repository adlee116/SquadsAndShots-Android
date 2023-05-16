package com.squadsandshots_android.presentation.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Create: Screen("create")
}
