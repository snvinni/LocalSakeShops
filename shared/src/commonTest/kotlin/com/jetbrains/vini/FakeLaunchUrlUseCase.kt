package com.jetbrains.vini

import com.jetbrains.vini.usecase.LaunchUrlUseCase

class FakeLaunchUrlUseCase: LaunchUrlUseCase {
    override suspend fun invoke(url: String) {
        println("deep link: $url")
    }
}