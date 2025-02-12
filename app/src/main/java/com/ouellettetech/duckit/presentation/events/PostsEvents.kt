package com.ouellettetech.duckit.presentation.events

sealed class PostsEvents {
    data class UpVote(val id: String) : PostsEvents()
    data class DownVote(val id: String) : PostsEvents()
    data object SigninButtonPressed : PostsEvents()
    data object NetworkError : PostsEvents()
    data object DismissNetworkError : PostsEvents()
    data object AddPost : PostsEvents()
}