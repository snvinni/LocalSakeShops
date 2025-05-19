package com.jetbrains.vini.di

import com.jetbrains.vini.usecase.LaunchUrlUseCase
import com.jetbrains.vini.usecase.LaunchUrlUseCaseImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::LaunchUrlUseCaseImpl) bind LaunchUrlUseCase::class
}
