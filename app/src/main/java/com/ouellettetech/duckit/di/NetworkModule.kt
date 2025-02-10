package com.ouellettetech.duckit.di

import android.content.Context
import com.ouellettetech.duckit.networking.DuckitApiService
import com.ouellettetech.duckit.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module@InstallIn(SingletonComponent::class)
open class NetworkModule {
    protected open fun baseUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        var builder = OkHttpClient.Builder()
        //    .addInterceptor(AddAuthInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    @Named("Retrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("Retrofit") retrofit: Retrofit): DuckitApiService {
        return retrofit.create(DuckitApiService::class.java)
    }
}