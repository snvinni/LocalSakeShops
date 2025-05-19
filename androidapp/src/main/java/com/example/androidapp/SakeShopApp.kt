package com.example.androidapp

import android.app.Application
import android.content.Context
import com.jetbrains.vini.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class SakeShopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            appModule = module {
                single<Context> { this@SakeShopApp }
            },
        )
    }
}
