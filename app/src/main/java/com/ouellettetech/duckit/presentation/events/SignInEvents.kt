package com.ouellettetech.duckit.presentation.events

sealed class SignInEvents {
    data object SigninButtonPressed: SignInEvents()
    data object SignUpButtonPressed: SignInEvents()
    data object NetworkError: SignInEvents()
    data object AccountExists: SignInEvents()
    data object PasswordIncorrect: SignInEvents()
    data object AccountNotFound: SignInEvents()
    data object DissmissAccountNotFound: SignInEvents()
    data object DissmissPasswordIncorrect: SignInEvents()
    data object DismissAccountExists: SignInEvents()
    data object DismissNetworkError: SignInEvents()
}