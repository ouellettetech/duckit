package com.ouellettetech.duckit.domain.repository

import com.ouellettetech.duckit.data.model.DuckitPosts
import kotlinx.coroutines.flow.Flow

interface DuckitRepository {
    suspend fun getPosts(): DuckitPosts?
}