package com.jetbrains.vini.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jetbrains.vini.theme.Theme
import kotlin.math.roundToInt

@Composable
fun StarRating(
    rating: Double,
    modifier: Modifier = Modifier.wrapContentWidth(),
    starCount: Int = 5,
    starSize: Dp = 16.dp,
    starSpacing: Dp = 2.dp,
) {
    val scaled = (rating * 10f).roundToInt()
    val fullStars = (scaled / 10).coerceIn(0, starCount)
    val hasHalf = (scaled % 10) >= 5 && fullStars < starCount
    val emptyStars = starCount - fullStars - if (hasHalf) 1 else 0

    Row(modifier) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Full star",
                modifier = Modifier
                    .size(starSize)
                    .padding(end = starSpacing),
                tint = Theme.primaryPink
            )
        }
        if (hasHalf) {
            Icon(
                imageVector = Icons.Filled.StarHalf,
                contentDescription = "Half star",
                modifier = Modifier
                    .size(starSize)
                    .padding(end = starSpacing),
                tint = Theme.primaryPink
            )
        }
        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Filled.StarOutline,
                contentDescription = "Empty star",
                modifier = Modifier
                    .size(starSize)
                    .padding(end = starSpacing),
                tint = Theme.primaryPink
            )
        }
    }
}

