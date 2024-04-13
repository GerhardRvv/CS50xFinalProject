package com.gerhard.cs50x.di

import com.gerhard.cs50x.BuildConfig
import com.gerhard.cs50x.core.api.ApiTwitterDataService
import com.gerhard.cs50x.core.api.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun apiTwitterDataService(
        @Named("coreData") retrofit: Retrofit
    ): ApiTwitterDataService {
        return retrofit.create(ApiTwitterDataService::class.java)
    }

    @Singleton
    @Provides
    @Named("coreData")
    fun retrofitCoreData(
        @Named("twitter") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TWITTER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @Named("twitter")
    fun okHttpClientTwitter(
        okHttpClient: OkHttpClient,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return okHttpClient
            .newBuilder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}
