package com.ouellettetech.duckit.utils

import com.ouellettetech.duckit.BuildConfig

object Constants {
    const val HEADER_AUTHORIZATION = "Authorization"
    const val NETWORK_PASSWORD_INCORRECT = 403
    const val NETWORK_ACCOUNT_NOT_FOUND = 404
    const val SharedPrefName = "Duckit_pref"
    const val BASE_URL = BuildConfig.baseURL
    const val upVoteFontSize = 20
    const val SharedPrefTokenName = "DuckitToken"
    const val NETWORK_ACCOUNT_EXISTS = 409
}