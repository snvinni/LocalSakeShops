package com.jetbrains.vini.usecase

import android.content.Context
import android.content.ContextWrapper
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import android.content.Intent
import android.net.Uri
import org.junit.Test
import kotlin.test.assertTrue

class LaunchUrlUseCaseImplAndroidTest {

    private class RecordingContext(base: Context) : ContextWrapper(base) {
        var lastIntent: Intent? = null

        override fun startActivity(intent: Intent) {
            lastIntent = intent
        }
    }

    @Test
    fun invokeShouldStartVIEWIntentWithCorrectDataAndFlags() = runBlocking {
        val appCtx = ApplicationProvider.getApplicationContext<Context>()
        val recordingCtx = RecordingContext(appCtx)
        val useCase = LaunchUrlUseCaseImpl(recordingCtx)

        val url = "https://example.com/foo"

        useCase.invoke(url)

        val fired: Intent? = recordingCtx.lastIntent
            ?: return@runBlocking

        assertEquals(Intent.ACTION_VIEW, fired?.action)
        assertEquals(Uri.parse(url), fired?.data)
        assertTrue(
            fired?.flags?.and(Intent.FLAG_ACTIVITY_NEW_TASK) != 0,
            "Expected FLAG_ACTIVITY_NEW_TASK to be set"
        )
    }
}
