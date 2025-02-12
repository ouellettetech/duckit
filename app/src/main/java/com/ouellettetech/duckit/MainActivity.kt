package com.ouellettetech.duckit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ouellettetech.duckit.presentation.navigation.AppNavigation
import com.ouellettetech.duckit.ui.theme.DuckitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuckitTheme {
                DuckitApp()
            }
        }
    }
}


@Composable
fun DuckitApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppNavigation(navController = rememberNavController())
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DuckitAppPreview() {
    DuckitTheme {
        DuckitApp()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DuckitTheme {
        Greeting("Android")
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    MessageCard("There")
}