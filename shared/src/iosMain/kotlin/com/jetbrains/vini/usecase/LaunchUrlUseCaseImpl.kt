package com.jetbrains.vini.usecase

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

/**
 * iOS actual implementation of [LaunchUrlUseCase].
 *
 * Parses the string into an [NSURL], then dispatches to
 * `UIApplication.sharedApplication().openURL(_:)`.
 */
actual class LaunchUrlUseCaseImpl : LaunchUrlUseCase {
    actual override suspend fun invoke(url: String) {
        val nsUrl: NSURL? = NSURL.URLWithString(url)
        if (nsUrl != null)
            UIApplication.sharedApplication().openURL(nsUrl)
    }
}