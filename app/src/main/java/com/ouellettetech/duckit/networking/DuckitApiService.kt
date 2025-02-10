package com.ouellettetech.duckit.networking

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface DuckitApiService {
    @POST("myaddress")
    suspend fun signIn(
        @Path("argument") arg1: String,
        @Body requestMessage: String, //actuall object
    ): Response<String>//Response Object

}