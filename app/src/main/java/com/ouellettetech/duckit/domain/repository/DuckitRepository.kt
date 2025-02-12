package com.ouellettetech.duckit.domain.repository

import com.ouellettetech.duckit.data.model.DuckitPosts

interface DuckitRepository {
    suspend fun getPosts(): DuckitPosts?
}