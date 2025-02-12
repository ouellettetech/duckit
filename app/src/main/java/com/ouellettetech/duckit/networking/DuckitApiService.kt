package com.ouellettetech.duckit.networking

import com.ouellettetech.duckit.data.model.DuckitPosts
import com.ouellettetech.duckit.data.model.SignUpRequest
import com.ouellettetech.duckit.data.model.Token
import com.ouellettetech.duckit.data.model.upVoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DuckitApiService {
    @POST(APIEndPoints.SIGN_IN)
    suspend fun signIn(
        @Body signUpRequest: SignUpRequest,
    ): Response<Token>//Response Object

    @GET(APIEndPoints.POSTS)
    suspend fun getPosts(): DuckitPosts?

    @POST(APIEndPoints.SIGN_UP)
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest,
    ): Response<Token>

    @POST(APIEndPoints.UP_VOTE)
    suspend fun upVote(
        @Path("id") id: String,
    ): Response<upVoteResponse>

    @POST(APIEndPoints.DOWN_VOTE)
    suspend fun downVote(
        @Path("id") id: String,
    ): Response<upVoteResponse>

}