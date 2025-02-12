package com.ouellettetech.duckit.data.repository

import com.ouellettetech.duckit.data.model.DuckitPosts
import com.ouellettetech.duckit.domain.repository.DuckitRepository
import com.ouellettetech.duckit.networking.DuckitApiService
import javax.inject.Inject

class DuckitRepositoryImpl @Inject constructor(
    private val apiService: DuckitApiService,
) : DuckitRepository {

    override suspend fun getPosts(): DuckitPosts? {
        // Fetch data from remote
        return apiService.getPosts()
    }
}