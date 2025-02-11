package com.ouellettetech.duckit.presentation.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ouellettetech.duckit.presentation.events.SignInEvents
import com.ouellettetech.duckit.presentation.viewModel.SignInViewModel

@Composable
fun SignInScreen(navController: NavController){
    val viewModel: SignInViewModel = hiltViewModel()
    viewModel.setNavController(navController)
    viewModel.getPosts()
    Text(text = "SignIn Screen 2")
    Button(
        onClick = {viewModel.onEvent(SignInEvents.SigninButtonPressed)},
    )  { Text("SignIn") }
}
