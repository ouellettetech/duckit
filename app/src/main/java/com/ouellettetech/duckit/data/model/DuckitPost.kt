package com.ouellettetech.duckit.data.model

data class DuckitPosts(
    val Posts: List<DuckitPost>
)
data class DuckitPost(
    val id: String,
    val headline: String,
    val image: String,
    val upvotes: Int,
    val author: String,
)