package com.ouellettetech.duckit.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ouellettetech.duckit.presentation.viewModel.SplashViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(navController: NavController){
    val viewModel: SplashViewModel = hiltViewModel()
    viewModel.setNavController(navController)
    Text(text = "Splash Screen Hilt")
    viewModel.startTimer()
}
