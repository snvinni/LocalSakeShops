package com.jetbrains.vini.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Theme {
    val hardBlack = Color(0xFF000000)
    val mainBlack = Color(0xFF333333)
    val primaryGreen = Color(0xFFB4E3AC)
    val primaryPink = Color(0xFFFFDDE3)
    val mainWhite = Color(0xFFFFFFFF)
    val lightGray = Color(0xFFEEEEEE)

    val LightScheme = lightColorScheme(
        primary = primaryGreen,
        onPrimary = mainBlack,
        secondary = primaryPink,
        onSecondary = mainBlack,
        background = mainWhite,
        onBackground = mainBlack,
        surface = mainWhite,
        onSurface = mainBlack,
        surfaceVariant = lightGray,
        onSurfaceVariant = mainBlack,
        primaryContainer = lightGray
    )

    val DarkScheme = darkColorScheme(
        primary = primaryGreen,
        onPrimary = mainBlack,
        secondary = primaryPink,
        onSecondary = mainWhite,
        background = hardBlack,
        onBackground = mainWhite,
        surface = mainBlack,
        onSurface = mainWhite,
        surfaceVariant = mainBlack,
        onSurfaceVariant = primaryGreen,
        surfaceContainer = primaryPink

    )

    @Composable
    fun SakeShopTheme(
        isDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        MaterialTheme(
            colorScheme = if (isDarkTheme) DarkScheme else LightScheme,
            typography = PoppingTypography(),
            content = content
        )
    }
}