package com.ouellettetech.duckit.presentation.events

sealed class postsEvents {
    data object SigninButtonPressed: postsEvents()
    data object NetworkError: postsEvents()
}