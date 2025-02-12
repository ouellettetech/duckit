package com.ouellettetech.duckit.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ouellettetech.duckit.R
import com.ouellettetech.duckit.presentation.events.AddPostEvents
import com.ouellettetech.duckit.presentation.uiState.AddPostUIState
import com.ouellettetech.duckit.presentation.viewModel.AddPostViewModel

@Composable
fun AddPostScreen(
    uiState: AddPostUIState,
    navController: NavController
) {

    val viewModel: AddPostViewModel = hiltViewModel()
    viewModel.setNavController(navController)
    if (uiState.networkdialog) {
        ErrorDialog(stringResource(R.string.NetworkErrorMessage)) { viewModel.onEvent(AddPostEvents.DismissNetworkError) }
    }

    AddPost(uiState, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPost(uiState: AddPostUIState, viewModel: AddPostViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    Button(
                        onClick = { viewModel.onEvent(AddPostEvents.Close) },
                    ) {
                        Text("Close")
                    }
                },
            )
        }
    ) { padding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(padding)
        ) {
            Text(text = stringResource(R.string.CreateNewPostTitle))
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                value = uiState.headline,
                onValueChange = {
                    viewModel.headerUpdate(it)
                },
                label = { Text(stringResource(R.string.headerHint)) }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                value = uiState.url,
                onValueChange = {
                    viewModel.imageUpdate(it)
                },
                label = { Text(stringResource(R.string.imageHint)) }
            )
            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = { viewModel.onEvent(AddPostEvents.AddPostButtonPressed) },
            ) {
                Text(stringResource(R.string.Submit))
            }
        }
    }
}