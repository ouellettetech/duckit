package com.ouellettetech.duckit.networking

import android.content.SharedPreferences
import com.ouellettetech.duckit.utils.Constants
import com.ouellettetech.duckit.utils.Constants.SharedPrefTokenName
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor
@Inject
constructor(var sharedPreferences: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(SharedPrefTokenName, "")
        var request = if (token.isNullOrEmpty()) {
            chain.request()
        } else {
            chain.request().newBuilder()
                .addHeader(Constants.HEADER_AUTHORIZATION, "Bearer $token")
                .build()

        }

        val response: Response = chain.proceed(request = request)
        return response
    }

}