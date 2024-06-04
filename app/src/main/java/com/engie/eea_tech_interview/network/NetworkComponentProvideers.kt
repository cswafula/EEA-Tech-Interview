package com.engie.eea_tech_interview.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10MB
const val READ_TIME_OUT: Long = 30
const val WRITE_TIME_OUT: Long = 10
const val CONNECT_TIME_OUT: Long = 10

fun createRetrofit(
    baseUrl: String,
    converterFactory: Converter.Factory,
    client: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
}

fun createOkHttpClient(context: Context): OkHttpClient {

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.HEADERS
    logging.level = HttpLoggingInterceptor.Level.BODY


    val clientBuilder = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, CACHE_SIZE))

    return clientBuilder.build()
}

fun createGsonConverter(): Converter.Factory =
    GsonConverterFactory.create()