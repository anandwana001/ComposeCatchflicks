package com.akshay.composecatchflicks.di

import com.akshay.composecatchflicks.BuildConfig
import com.akshay.composecatchflicks.data.remote.NetworkAdapterFactory
import com.akshay.composecatchflicks.data.remote.NetworkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

private val json = Json { ignoreUnknownKeys = true }

/**
 * Created by anandwana001 on
 * 15, November, 2022
 **/
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    /**
     * The [HttpLoggingInterceptor] helps in logging the request and response data.
     * Attaching the interceptor only in the debug mode.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(NetworkAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)
}
