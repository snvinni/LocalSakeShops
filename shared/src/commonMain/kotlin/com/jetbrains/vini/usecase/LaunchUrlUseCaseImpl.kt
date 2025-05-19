package com.jetbrains.vini.usecase

expect class LaunchUrlUseCaseImpl : LaunchUrlUseCase {
    override suspend fun invoke(url: String)
}