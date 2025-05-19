package com.jetbrains.vini.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jetbrains.vini.component.PlaceButton
import com.jetbrains.vini.component.StarRating
import com.jetbrains.vini.domain.SakeShop
import com.jetbrains.vini.theme.Size.default
import com.jetbrains.vini.theme.Size.mini
import com.jetbrains.vini.theme.Size.small
import local_sake_shop.shared.generated.resources.Res
import local_sake_shop.shared.generated.resources.fallback
import org.jetbrains.compose.resources.painterResource

@Composable
fun ListContent(
    shops: List<SakeShop>,
    onShopClicked: (Int, String) -> Unit,
    onLaunchUrl: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(shops.size, key = { shops[it].id }) { shop ->
            ShopCard(
                shop = shops[shop],
                onClick = { onShopClicked(shop, shops[shop].name) },
                onLaunchUrl = onLaunchUrl
            )
        }
    }
}

@Composable
fun ShopCard(
    shop: SakeShop,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLaunchUrl: (String) -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(small),
        shape = RoundedCornerShape(default),
        elevation = cardElevation(defaultElevation = mini),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(default),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = shop.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(mini))

                Text(
                    text = shop.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(small))

                StarRating(
                    rating = shop.rating,
                )

                Spacer(Modifier.height(small))

                PlaceButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onLaunchUrl(shop.googleMapsLink)
                    },
                    text = shop.address
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.width(default))

            AsyncImage(
                error = painterResource(Res.drawable.fallback),
                fallback = painterResource(Res.drawable.fallback),
                placeholder = painterResource(Res.drawable.fallback),
                model = shop.picture,
                contentDescription = shop.name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .sizeIn(maxWidth = 100.dp, maxHeight = 100.dp)
                    .weight(0.7f)
                    .align(Alignment.CenterVertically)
                    .padding(end = mini, start = small)
                    .clip(RoundedCornerShape(small))
                    .background(Transparent)
            )
        }
    }
}