package com.ouellettetech.duckit.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ouellettetech.duckit.R
import com.ouellettetech.duckit.presentation.events.SignInEvents
import com.ouellettetech.duckit.presentation.uiState.SignInUIState
import com.ouellettetech.duckit.presentation.viewModel.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    uiState: SignInUIState,
    navController: NavController
) {

    val viewModel: SignInViewModel = hiltViewModel()
    viewModel.setNavController(navController)

    if (uiState.networkdialog) {
        ErrorDialog(stringResource(R.string.NetworkErrorMessage)) { viewModel.onEvent(SignInEvents.DismissNetworkError) }
    }

    if (uiState.accountExists) {
        ErrorDialog(stringResource(R.string.AccountExistsMessage)) { viewModel.onEvent(SignInEvents.DismissAccountExists) }
    }

    if (uiState.passwordIncorrect) {
        ErrorDialog(stringResource(R.string.PasswordIncorrectMessage)) {
            viewModel.onEvent(
                SignInEvents.DissmissPasswordIncorrect
            )
        }
    }

    if (uiState.accountNotFound) {
        ErrorDialog(stringResource(R.string.AccountNotFoundMessage)) {
            viewModel.onEvent(
                SignInEvents.DissmissAccountNotFound
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { padding ->
        Surface(
            Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            SignInPrompt(viewModel)
        }
    }

}

@Composable
fun SignInPrompt(viewModel: SignInViewModel) {
    var email by rememberSaveable { mutableStateOf("") } //These should be in the UIState...
    var password by rememberSaveable { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.loginTitle))
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = email,
            onValueChange = {
                email = it
                viewModel.emailUpdate(it)
            },
            label = { Text(stringResource(R.string.emailHint)) }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
                viewModel.passwordUpdate(it)
            },
            label = { Text(stringResource(R.string.passwordHint)) }
        )
        Spacer(modifier = Modifier.padding(20.dp))

        Row {
            Button(
                onClick = { viewModel.onEvent(SignInEvents.SigninButtonPressed) },
            ) {
                Text("SignIn")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { viewModel.onEvent(SignInEvents.SignUpButtonPressed) },
            ) {
                Text("SignUp")
            }

        }
    }
}