package com.jetbrains.vini

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jetbrains.vini.ui.detail.DetailScreen
import com.jetbrains.vini.ui.list.ListScreen
import com.jetbrains.vini.theme.Theme.SakeShopTheme
import kotlinx.serialization.Serializable

@Serializable
object ListDestination

@Serializable
data class DetailDestination(val shopId: Int, val shopName: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    SakeShopTheme(
        isDarkTheme = isSystemInDarkTheme()
    ) {
        val navController = rememberNavController()

        NavHost(navController, startDestination = ListDestination) {
            composable<ListDestination> {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.surface,
                    topBar = {
                        Column {
                            CenterAlignedTopAppBar(
                                title = {
                                    Text(
                                        "Local Sake Shops",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            )
                        }
                    }
                ) { padding ->
                    ListScreen(
                        navigateToDetails = { shopId, shopName ->
                            navController.navigate(
                                DetailDestination(
                                    shopId,
                                    shopName
                                )
                            )
                        },
                        modifier = Modifier
                            .padding(padding)
                    )
                }
            }

            // —— DETAIL SCREEN ————————————————————————————————————————
            composable<DetailDestination> { backStack ->
                val shopId = backStack.toRoute<DetailDestination>().shopId
                val shopName = backStack.toRoute<DetailDestination>().shopName
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.surface,
                    topBar = {
                        TopAppBar(
                            title = { Text(shopName) },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
                    }
                ) { padding ->
                    DetailScreen(
                        modifier = Modifier.padding(padding),
                        shopId = shopId,
                    )
                }
            }
        }
    }
}
