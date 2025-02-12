package com.ouellettetech.duckit.presentation.uiState


data class AddPostUIState(
    var loading: Boolean = false,
    var networkdialog: Boolean = false,
    var loggedIn: Boolean,
    var headline: String = "",
    var url: String = "",
)