package com.jetbrains.vini.ui.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetbrains.vini.component.EmptyScreenContent
import com.jetbrains.vini.component.LoadingScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreen(
    shopId: Int,
    modifier: Modifier
) {
    val viewModel = koinViewModel<DetailViewModel>(parameters = { parametersOf(shopId) })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    with(uiState) {
        AnimatedContent(sakeShop != null) { hasShopData ->
            when {
                hasShopData -> {
                    DetailScreenContent(
                        shop = uiState.sakeShop ?: run {
                            EmptyScreenContent(
                                modifier = modifier
                            )
                            return@AnimatedContent
                        },
                        modifier = modifier,
                        onLaunchUrl = viewModel::launchUrl
                    )
                }

                isLoading -> {
                    LoadingScreen(
                        modifier = modifier
                    )
                }

                hasFailed -> {
                    EmptyScreenContent(
                        modifier = modifier
                    )
                }
            }
        }
    }
}