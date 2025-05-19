package com.jetbrains.vini.usecase

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

actual class LaunchUrlUseCaseImpl(
    private val context: Context
) : LaunchUrlUseCase {
    actual override suspend fun invoke(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}