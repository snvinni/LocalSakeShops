package com.jetbrains.vini.ui.details

import com.jetbrains.vini.FakeLaunchUrlUseCase
import com.jetbrains.vini.FakeSakeShopRepository
import com.jetbrains.vini.domain.SakeShop
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
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class DetailViewModelTest {
    private val scheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(scheduler)
    private val repository = FakeSakeShopRepository()
    private val useCase = FakeLaunchUrlUseCase()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialStateIsLoading() = runTest {
        val viewModel = DetailViewModel(
            repository,
            1,
            useCase
        )
        val job = launch(testDispatcher) { viewModel.uiState.collect { } }

        assertTrue(viewModel.uiState.value.isLoading)
        assertFalse(viewModel.uiState.value.hasFailed)
        assertTrue(viewModel.uiState.value.sakeShop == null)

        job.cancel()
    }


    @Test
    fun getSakeShopSuccessUpdatesUiState() = runTest {
        val dummyShop = SakeShop(
            id = 1,
            name = "Sake Shop 1",
            address = "123 Main St",
            coordinates = listOf(37.7749, -122.4194),
            description = "Description 1",
            googleMapsLink = "https://www.google.com/maps",
            picture = "https://via.placeholder.com/150",
            rating = 4.5,
            website = "https://www.example.com"
        )

        val viewModel = DetailViewModel(
            repository,
            1,
            useCase
        )

        val job = launch(testDispatcher) { viewModel.uiState.collect {} }

        scheduler.advanceUntilIdle()

        assertEquals(dummyShop, viewModel.uiState.value.sakeShop)
        assertFalse(viewModel.uiState.value.isLoading)
        assertFalse(viewModel.uiState.value.hasFailed)

        job.cancel()
    }

    @Test
    fun getSakeShopFailureUpdatesUiState() = runTest {
        val repository = FakeSakeShopRepository(FakeSakeShopRepository.ResponseType.Failure)
        val viewModel = DetailViewModel(
            repository,
            1,
            useCase
        )

        val job = launch(testDispatcher) { viewModel.uiState.collect {} }

        scheduler.advanceUntilIdle()

        assertEquals(null, viewModel.uiState.value.sakeShop)
        assertFalse(viewModel.uiState.value.isLoading)
        assertTrue(viewModel.uiState.value.hasFailed)

        job.cancel()
    }

    @Test
    fun launchUrlInvokesUseCase() = runTest {
        val fakeShop = SakeShop(
            id = 1,
            name = "Sake Shop 1",
            address = "123 Main St",
            coordinates = listOf(37.7749, -122.4194),
            description = "Description 1",
            googleMapsLink = "https://www.google.com/maps",
            picture = "https://via.placeholder.com/150",
            rating = 4.5,
            website = "https://www.example.com"
        )
        val viewModel = DetailViewModel(
            repository,
            1,
            useCase
        )
        viewModel.launchUrl(fakeShop.googleMapsLink)

        scheduler.advanceUntilIdle()

        assertEquals("https://www.google.com/maps", fakeShop.googleMapsLink)
    }
}