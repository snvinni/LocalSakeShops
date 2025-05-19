package com.jetbrains.vini.usecase

/**
 * ─────────────────────────────────────────────────────────────────────*
 * • Purpose
 *   Provides a platform-specific way to launch a URL from shared code.
 *
 * • Shared/common
 *   - Declares an `expect class LaunchUrlUseCaseImpl : LaunchUrlUseCase`
 *     so your business logic can just call `LaunchUrlUseCaseImpl(url)`
 *     without worrying about the platform.
 *
 * • iOS (iosMain)
 *   - Uses `NSURL.URLWithString(url)` to safely parse the string.
 *   - Calls `UIApplication.sharedApplication().openURL(nsUrl)` to hand
 *     off to Safari or whatever the system’s default browser/app is.
 *   - Make sure you’ve linked the UIKit framework in your Xcode target.
 *
 * • Android (androidMain)
 *   - Builds an `Intent(Intent.ACTION_VIEW, url.toUri())`.
 *   - Adds `FLAG_ACTIVITY_NEW_TASK` so it works from a non-Activity context.
 *   - You’ll need the standard `android.permission.INTERNET` in your
 *     AndroidManifest (though opening a URL in browser won’t typically
 *     require extra permissions).
 * ─────────────────────────────────────────────────────────────────────
 */
expect class LaunchUrlUseCaseImpl : LaunchUrlUseCase {
    override suspend fun invoke(url: String)
}