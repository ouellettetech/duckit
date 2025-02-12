package com.ouellettetech.duckit.presentation.events

sealed class postsEvents {
    data class UpVote(val id: String) : postsEvents()
    data class DownVote(val id: String) : postsEvents()
    data object SigninButtonPressed : postsEvents()
    data object NetworkError : postsEvents()
    data object DismissNetworkError : postsEvents()
}