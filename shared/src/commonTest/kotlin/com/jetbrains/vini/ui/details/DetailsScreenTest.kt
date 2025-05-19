package com.jetbrains.vini.ui.details

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.jetbrains.vini.FakeLaunchUrlUseCase
import com.jetbrains.vini.FakeSakeShopRepository
import com.jetbrains.vini.ui.detail.DetailScreen
import com.jetbrains.vini.ui.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class DetailsScreenTest {
    private val scheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(scheduler)

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun showsLoadingWhenLoading() = runTest {
        runComposeUiTest {
            val viewModel = getViewModel(FakeSakeShopRepository.ResponseType.Success)

            val job = launch(testDispatcher) { viewModel.uiState.collect { } }

            setContent {
                DetailScreen(
                    shopId = 1,
                    modifier = Modifier
                )
            }

            onNodeWithTag("loading").assertIsDisplayed()

            job.cancel()
        }
    }

    private fun getViewModel(responseType: FakeSakeShopRepository.ResponseType) = DetailViewModel(
        FakeSakeShopRepository(responseType),
        1,
        FakeLaunchUrlUseCase()
    )
}
