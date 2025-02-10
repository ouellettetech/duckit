package com.ouellettetech.duckit.presentation.viewModel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ouellettetech.duckit.networking.DuckitApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    application: Application,
    private val duckitApiService: DuckitApiService
) : DuckitViewModel() {
    
    
    private fun signInUser() {
        viewModelScope.launch {
            duckitApiService.signIn(
                "",
                requestMessage = TODO()
            );
        }
    }

}