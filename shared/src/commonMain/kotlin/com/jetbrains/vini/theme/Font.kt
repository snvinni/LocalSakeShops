package com.jetbrains.vini.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import local_sake_shop.shared.generated.resources.Poppins_Bold
import local_sake_shop.shared.generated.resources.Poppins_Medium
import local_sake_shop.shared.generated.resources.Poppins_Regular
import local_sake_shop.shared.generated.resources.Poppins_SemiBold
import local_sake_shop.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun PoppinsFontFamily() = FontFamily(
    Font(Res.font.Poppins_Regular, FontWeight.Normal),
    Font(Res.font.Poppins_Bold, FontWeight.Bold),
    Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
    Font(Res.font.Poppins_Medium, FontWeight.Medium),
)

@Composable
fun PoppingTypography() =
    Typography().run {
        val fontFamily = PoppinsFontFamily()

        copy(
            displayLarge = displayLarge.copy(fontFamily = fontFamily),
            displayMedium = displayMedium.copy(fontFamily = fontFamily),
            displaySmall = displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
            titleLarge = titleLarge.copy(fontFamily = fontFamily),
            titleMedium = titleMedium.copy(fontFamily = fontFamily),
            titleSmall = titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = bodySmall.copy(fontFamily = fontFamily),
            labelLarge = labelLarge.copy(fontFamily = fontFamily),
            labelMedium = labelMedium.copy(fontFamily = fontFamily),
            labelSmall = labelSmall.copy(fontFamily = fontFamily)
        )

    }