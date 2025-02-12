package com.ouellettetech.duckit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ouellettetech.duckit.presentation.screens.AddPostScreen
import com.ouellettetech.duckit.presentation.screens.PostsScreen
import com.ouellettetech.duckit.presentation.screens.SignInScreen
import com.ouellettetech.duckit.presentation.uiState.AddPostUIState
import com.ouellettetech.duckit.presentation.uiState.PostsUIState
import com.ouellettetech.duckit.presentation.uiState.SignInUIState
import com.ouellettetech.duckit.presentation.viewModel.AddPostViewModel
import com.ouellettetech.duckit.presentation.viewModel.PostsViewModel
import com.ouellettetech.duckit.presentation.viewModel.SignInViewModel

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

        composable(NavigationItem.SignInScreen.route) {
            val viewModel: SignInViewModel = hiltViewModel()
            val uiState: SignInUIState by viewModel.state.collectAsState()
            SignInScreen(uiState = uiState, navController = navController)
        }
        composable(NavigationItem.PostsScreen.route) {
            val viewModel: PostsViewModel = hiltViewModel()
            val uiState: PostsUIState by viewModel.state.collectAsState()

            PostsScreen(uiState = uiState, navController = navController)
        }
        composable(NavigationItem.AddPostScreen.route) {
            val viewModel: AddPostViewModel = hiltViewModel()
            val uiState: AddPostUIState by viewModel.state.collectAsState()

            AddPostScreen(uiState = uiState, navController = navController)
        }
        //composable(NavigationItem.Upvote.route,
        //  arguments = listOf(navArgument("userId") {
        // type = NavType.StringType
        //  ) {
        //            SignInScreen(navController)
        //        }
    }
}