package com.jetbrains.vini.ui.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetbrains.vini.component.EmptyScreenContent
import com.jetbrains.vini.component.LoadingScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListScreen(
    navigateToDetails: (shopId: Int, shopName: String) -> Unit,
    modifier: Modifier
) {
    val viewModel = koinViewModel<ListViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    with(uiState) {
        AnimatedContent(sakeShops.isNotEmpty()) { isListAvailable ->
            when {
                isListAvailable -> {
                    ListContent(
                        modifier = modifier,
                        shops = sakeShops,
                        onShopClicked = navigateToDetails,
                        onLaunchUrl = viewModel::launchUrl
                    )
                }

                isLoading -> {
                    LoadingScreen(
                        modifier = modifier
                    )
                }

                hasFailed -> {
                    EmptyScreenContent(modifier = modifier)
                }
            }
        }
    }
}