package com.ouellettetech.duckit.networking

import android.content.SharedPreferences
import android.util.Log
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
        var request = if (token.isNullOrEmpty() ||
            chain.request().url.toString().contains("/signin") ||
            chain.request().url.toString().contains("/signup")
        ) {
            chain.request()
        } else {
            chain.request().newBuilder()
                .addHeader(Constants.HEADER_AUTHORIZATION, "Bearer $token")
                .build()

        }
        Log.d("Network:", "Url: ${request.url} Headers: ${request.headers}")

        val response: Response = chain.proceed(request = request)
        return response
    }

}