package com.gerhard.cs50x.core.api

import com.gerhard.cs50x.core.SessionManager
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    companion object {
        const val RAPID_KEY = "X-RapidAPI-Key"
        const val RAPID_HOST = "X-RapidAPI-Host"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        sessionManager.fetchApiKey()?.let { key ->
            requestBuilder.header(RAPID_KEY, key)
        }

        sessionManager.fetchApiHost()?.let { host ->
            requestBuilder.header(RAPID_HOST, host)
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
