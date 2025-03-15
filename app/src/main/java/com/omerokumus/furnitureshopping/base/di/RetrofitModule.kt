package com.omerokumus.furnitureshopping.base.di

import com.omerokumus.furnitureshopping.base.constants.Constants
import com.omerokumus.furnitureshopping.base.constants.Constants.OK_HTTP_DEFAULT_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideDefaultOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(OK_HTTP_DEFAULT_TIMEOUT, TimeUnit.SECONDS) // Increased from default 10s
            .readTimeout(OK_HTTP_DEFAULT_TIMEOUT, TimeUnit.SECONDS)    // Increased from default 10s
            .writeTimeout(OK_HTTP_DEFAULT_TIMEOUT, TimeUnit.SECONDS)   // Increased from default 10s
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


}