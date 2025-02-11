package com.ouellettetech.duckit.networking

import com.ouellettetech.duckit.data.model.DuckitPosts
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DuckitApiService {
    @POST(APIEndPoints.SIGN_IN)
    suspend fun signIn(
        @Path("argument") arg1: String,
        @Body requestMessage: String, //actual object
    ): Response<String>//Response Object

    @GET(APIEndPoints.POSTS)
    suspend fun getPosts(): DuckitPosts?

}