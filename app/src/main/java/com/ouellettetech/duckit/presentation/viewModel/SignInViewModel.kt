package com.ouellettetech.duckit.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ouellettetech.duckit.networking.DuckitApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    application: Application,
    private val duckitApiService: DuckitApiService
) : DuckitViewModel() {
    private lateinit var mNavController: NavController
    private var loading: Boolean = false
    
    
    private fun signInUser() {
        viewModelScope.launch {
            duckitApiService.signIn(
                "",
                requestMessage = TODO()
            );
        }
    }

    fun getPosts() {
        if(!loading) {
            loading = true
            viewModelScope.launch {
                try {
                    Log.d("Network", duckitApiService.getPosts().toString())
                    Log.d("Network", "After Network request")
                } catch (ex: Exception){
                    Log.e("Network", "Error Getting Data",ex)
                }
            }
        }
    }

    fun setNavController(nav: NavController) {
        mNavController = nav
    }

}