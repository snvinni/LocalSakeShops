package com.jetbrains.vini.ui.list

import com.jetbrains.vini.FakeLaunchUrlUseCase
import com.jetbrains.vini.FakeSakeShopRepository
import com.jetbrains.vini.domain.SakeShop
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
class ListViewModelTest {
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
        val viewModel = ListViewModel(repository, useCase)
        val job = launch(testDispatcher) { viewModel.uiState.collect { } }

        assertTrue(viewModel.uiState.value.isLoading)
        assertFalse(viewModel.uiState.value.hasFailed)
        assertTrue(viewModel.uiState.value.sakeShops.isEmpty())

        job.cancel()
    }


    @Test
    fun loadSakeShopsSuccessUpdatesUiState() = runTest {
        // Arrange
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

        val viewModel = ListViewModel(repository, useCase)

        val job = launch(testDispatcher) { viewModel.uiState.collect {} }

        scheduler.advanceUntilIdle()

        assertEquals(listOf(dummyShop), viewModel.uiState.value.sakeShops)
        assertFalse(viewModel.uiState.value.isLoading)
        assertFalse(viewModel.uiState.value.hasFailed)

        job.cancel()
    }

    @Test
    fun loadSakeShopsFailureUpdatesUiState() = runTest {
        val repository = FakeSakeShopRepository(FakeSakeShopRepository.ResponseType.Failure)
        val viewModel = ListViewModel(repository, useCase)

        val job = launch(testDispatcher) { viewModel.uiState.collect {} }

        scheduler.advanceUntilIdle()

        assertEquals(listOf(), viewModel.uiState.value.sakeShops)
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
        val viewModel = ListViewModel(repository, useCase)
        viewModel.launchUrl(fakeShop.googleMapsLink)

        scheduler.advanceUntilIdle()

        assertEquals("https://www.google.com/maps", fakeShop.googleMapsLink)
    }
}