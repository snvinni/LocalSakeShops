package com.jetbrains.vini.usecase

interface LaunchUrlUseCase {
    suspend operator fun invoke(url: String)
}