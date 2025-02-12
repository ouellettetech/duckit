package com.ouellettetech.duckit.presentation.uiState

import com.ouellettetech.duckit.data.model.DuckitPosts


data class PostsUIState(
    var posts: DuckitPosts? = null,
    var loading: Boolean = false,
    var networkdialog: Boolean = false,
    var loggedIn: Boolean,
){}