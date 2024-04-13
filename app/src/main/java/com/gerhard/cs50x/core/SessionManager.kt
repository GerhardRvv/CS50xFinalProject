package com.gerhard.cs50x.core

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.gerhard.cs50x.BuildConfig
import com.gerhard.cs50x.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext val context: Context
) {

    companion object {
        const val RAPID_API_KEY = "TWITTER_API_KEY"
        const val RAPID_API_HOST = "TWITTER_API_HOST"
    }

    private val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private var prefs = EncryptedSharedPreferences.create(
        context.getString(R.string.app_name),
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun fetchApiKey(): String? {
        return prefs.getString(RAPID_API_KEY, BuildConfig.RAPID_API_KEY)
    }

    fun fetchApiHost(): String? {
        return prefs.getString(RAPID_API_HOST, BuildConfig.RAPID_API_HOST)
    }
}
