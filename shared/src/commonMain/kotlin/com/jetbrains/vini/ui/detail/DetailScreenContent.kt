package com.jetbrains.vini.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.jetbrains.vini.component.PlaceButton
import com.jetbrains.vini.component.StarRating
import com.jetbrains.vini.domain.SakeShop
import com.jetbrains.vini.theme.Size
import com.jetbrains.vini.theme.Size.default
import com.jetbrains.vini.theme.Size.large
import com.jetbrains.vini.theme.Size.medium
import com.jetbrains.vini.theme.Size.mini
import com.jetbrains.vini.theme.Size.small
import com.jetbrains.vini.theme.Theme
import local_sake_shop.shared.generated.resources.Res
import local_sake_shop.shared.generated.resources.fallback
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailScreenContent(
    shop: SakeShop,
    onLaunchUrl: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(medium)
        ) {
            Card(
                shape = RoundedCornerShape(default),
                elevation = cardElevation(Size.mini),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                AsyncImage(
                    error = painterResource(Res.drawable.fallback),
                    fallback = painterResource(Res.drawable.fallback),
                    placeholder = painterResource(Res.drawable.fallback),
                    model = shop.picture,
                    contentDescription = shop.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .background(Transparent)
                        .fillMaxSize()
                )
            }

            Spacer(Modifier.height(default))

            Text(
                text = shop.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(small))

            StarRating(
                rating = shop.rating,
                starSize = large,
                starSpacing = mini
            )

            Spacer(Modifier.height(default))

            Text(
                text = shop.description,
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(Modifier.height(default))

            PlaceButton(
                onClick = { onLaunchUrl(shop.googleMapsLink) },
                text = shop.address
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Button(
            onClick = { onLaunchUrl(shop.website) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(default)
        ) {
            Text("Visit Website", color = Theme.mainWhite)
        }
    }
}
