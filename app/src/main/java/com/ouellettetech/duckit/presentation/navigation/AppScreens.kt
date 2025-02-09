package com.ouellettetech.duckit.presentation.navigation

enum class AppScreens {
    SplashScreen;

    companion object {
        fun fromRoute(route:String?) : AppScreens =
            when(route?.substringBefore("/")){
                SplashScreen.name -> SplashScreen
                null -> TODO( "Return to homescreen")
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}