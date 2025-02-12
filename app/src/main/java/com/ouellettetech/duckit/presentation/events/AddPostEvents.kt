package com.ouellettetech.duckit.presentation.events

sealed class AddPostEvents {
    data object AddPostButtonPressed : AddPostEvents()
    data object NetworkError : AddPostEvents()
    data object DismissNetworkError : AddPostEvents()
    data object Close : AddPostEvents()
}