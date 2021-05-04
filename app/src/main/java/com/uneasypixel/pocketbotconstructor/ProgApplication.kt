package com.uneasypixel.pocketbotconstructor

import android.app.Application
import com.vk.sdk.VKSdk

class ProgApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
    }
}