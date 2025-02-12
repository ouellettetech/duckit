package com.ouellettetech.duckit.presentation.uiState

data class SignInUIState(
    var loading: Boolean = false,
    var email: String = "",
    var password: String = "",
    var networkdialog: Boolean = false,
    var accountExists: Boolean = false,
    var passwordIncorrect:Boolean = false,
    var accountNotFound: Boolean = false,
){}