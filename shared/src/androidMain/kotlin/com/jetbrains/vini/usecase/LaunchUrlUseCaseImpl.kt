package com.jetbrains.vini.usecase

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

/**
 * Android actual implementation of [LaunchUrlUseCase].
 *
 * Wraps the URL in an ACTION_VIEW [Intent] and starts the system browser
 * or appropriate handler. Uses `FLAG_ACTIVITY_NEW_TASK` so it can be
 * called from any Context (including Application).
 *
 * Note: ensure you pass an Application or Activity context here to avoid leaks.
 */
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