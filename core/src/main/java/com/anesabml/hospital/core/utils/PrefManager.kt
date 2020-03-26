package com.anesabml.hospital.core.utils

import android.app.Application
import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefManager @Inject constructor(application: Application) {

    private val sharedPreferences =
        application.getSharedPreferences("hospital", Context.MODE_PRIVATE)

    var isUserLoggedIn: Boolean
        set(value) {
            sharedPreferences.edit().putBoolean("user_logged", value).apply()
        }
        get() = sharedPreferences.getBoolean("user_logged", false)


}