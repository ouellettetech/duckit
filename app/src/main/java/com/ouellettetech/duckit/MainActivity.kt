package com.ouellettetech.duckit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ouellettetech.duckit.presentation.navigation.AppNavigation
import com.ouellettetech.duckit.presentation.navigation.NavigationItem
import com.ouellettetech.duckit.ui.theme.DuckitTheme
import com.ouellettetech.duckit.utils.Constants
import com.ouellettetech.duckit.utils.Constants.SharedPrefTokenName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(Constants.SharedPrefName, Context.MODE_PRIVATE)
        enableEdgeToEdge()
        setContent {
            DuckitTheme {
                DuckitApp(sharedPreferences)
            }
        }
    }
}


@Composable
fun DuckitApp(sharedPreferences: SharedPreferences) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (sharedPreferences.getString(SharedPrefTokenName, "").isNullOrEmpty()) {
                AppNavigation(
                    navController = rememberNavController(),
                    startDestination = NavigationItem.SignInScreen.route
                )
            } else {
                AppNavigation(
                    navController = rememberNavController(),
                    startDestination = NavigationItem.PostsScreen.route
                )
            }

        }
    }
}

