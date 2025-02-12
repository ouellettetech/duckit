package com.ouellettetech.duckit.presentation.navigation

enum class Screen {
    SignInScreen,
    PostsScreen;
}

sealed class NavigationItem(val route: String) {
    object SignInScreen : NavigationItem(Screen.SignInScreen.name)
    object PostsScreen  : NavigationItem(Screen.PostsScreen.name)
}