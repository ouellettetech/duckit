package com.ouellettetech.duckit.presentation.navigation

enum class Screen {
    SplashScreen,
    SignInScreen;
}

sealed class NavigationItem(val route: String) {
    object SplashScreen : NavigationItem(Screen.SplashScreen.name)
    object SignInScreen : NavigationItem(Screen.SignInScreen.name)
}