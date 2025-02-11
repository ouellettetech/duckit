package com.ouellettetech.duckit.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ouellettetech.duckit.data.model.DuckitPosts
import com.ouellettetech.duckit.networking.DuckitApiService
import com.ouellettetech.duckit.presentation.events.SignInEvents
import com.ouellettetech.duckit.presentation.events.postsEvents
import com.ouellettetech.duckit.presentation.navigation.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class postsViewModel @Inject constructor(
    application: Application,
    private val duckitApiService: DuckitApiService,
) : DuckitViewModel() {
    private lateinit var mNavController: NavController
    var posts: DuckitPosts? = null //Should move to uiState object...
    var loading: Boolean = false
    var networkdialog = false


    fun getPosts() {
        if(!loading) {
            loading = true
            viewModelScope.launch {
                try {
                    posts=duckitApiService.getPosts()
                    Log.d("Network", "After Network request")
                } catch (ex: Exception){
                    Log.e("Network", "Error Getting Data",ex)
                    onEvent(postsEvents.NetworkError)
                }
            }
        }
    }

    fun setNavController(nav: NavController) {
        mNavController = nav
    }

    fun onEvent(event: postsEvents){
        when(event){
            postsEvents.SigninButtonPressed -> {
                mNavController.navigate(NavigationItem.SignInScreen.route)
            }
            postsEvents.NetworkError -> {
                networkdialog = true
            }
        }
    }

}