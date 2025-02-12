package com.ouellettetech.duckit.presentation.viewModel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
//import com.ouellettetech.duckit.presentation.navigation.AppScreens
import com.ouellettetech.duckit.presentation.navigation.NavigationItem
import com.ouellettetech.duckit.presentation.navigation.Screen
import com.ouellettetech.duckit.presentation.uiState.SignInUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application
) : DuckitViewModel<SignInUIState>() {
    private var runningTimer: Boolean = false
    private lateinit var mNavController: NavController
    override fun initState(): SignInUIState {
        TODO("Not yet implemented")
    }

    override fun onCreate(owner: LifecycleOwner){
        super.onCreate(owner)
    }

    fun startTimer() {
        if(!runningTimer) {
            runningTimer = true
            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    runningTimer = false
                    mNavController.navigate(NavigationItem.SignInScreen.route)
                }, 2000)
            }
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        runningTimer = false
    }

    fun setNavController(nav: NavController) {
        mNavController = nav
    }

}