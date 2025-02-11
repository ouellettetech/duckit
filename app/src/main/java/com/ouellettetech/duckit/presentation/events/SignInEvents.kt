package com.ouellettetech.duckit.presentation.events

sealed class SignInEvents {
    data object SigninButtonPressed: SignInEvents()
}