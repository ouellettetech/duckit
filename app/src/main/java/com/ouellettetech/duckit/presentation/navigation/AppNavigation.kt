package com.ouellettetech.duckit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ouellettetech.duckit.presentation.screens.SplashScreen
import com.ouellettetech.duckit.presentation.screens.SignInScreen
import com.ouellettetech.duckit.presentation.screens.PostsScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.SignInScreen.route
    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationItem.SplashScreen.route){
            SplashScreen(navController=navController)
        }
        composable(NavigationItem.SignInScreen.route) {
            SignInScreen(navController=navController)
        }
        composable(NavigationItem.PostsScreen.route) {
            PostsScreen(navController = navController)
        }
        //composable(NavigationItem.Upvote.route,
        //  arguments = listOf(navArgument("userId") {
        // type = NavType.StringType
        //  ) {
        //            SignInScreen(navController)
        //        }
    }
}