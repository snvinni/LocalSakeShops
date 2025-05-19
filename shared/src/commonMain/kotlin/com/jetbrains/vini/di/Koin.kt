package com.jetbrains.vini.di

import com.jetbrains.vini.data.KtorSakeShopApi
import com.jetbrains.vini.data.SakeShopApi
import com.jetbrains.vini.data.SakeShopRepositoryImpl
import com.jetbrains.vini.domain.SakeShopRepository
import com.jetbrains.vini.ui.detail.DetailViewModel
import com.jetbrains.vini.ui.list.ListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<SakeShopApi> { KtorSakeShopApi(get()) }
    single<SakeShopRepository> {
        SakeShopRepositoryImpl(
            sakeShopApi = get()
        )
    }
}

val viewModelModule = module {
    factoryOf(
        ::ListViewModel,
    )
    factoryOf(::DetailViewModel)
}

expect val platformModule: Module

fun initKoin() = initKoin(appModule = module {})

fun initKoin(appModule: Module) {
    startKoin {
        modules(
            appModule,
            platformModule,
            dataModule,
            viewModelModule,
        )
    }
}
