package com.jetbrains.vini.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.vini.core.Resource
import com.jetbrains.vini.usecase.LaunchUrlUseCase
import com.jetbrains.vini.domain.SakeShop
import com.jetbrains.vini.domain.SakeShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    private val sakeShopRepository: SakeShopRepository,
    private val launchUrlUseCase: LaunchUrlUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            ListUiState(
                sakeShops = emptyList(),
                isLoading = true,
                hasFailed = false
            )
        )

    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ListUiState(
            sakeShops = emptyList(),
            isLoading = true,
            hasFailed = false
        )
    )

    init {
        loadSakeShops()
    }

    fun launchUrl(url: String) = viewModelScope.launch {
        launchUrlUseCase(url)
    }

    private fun loadSakeShops() = viewModelScope.launch {
        val result = sakeShopRepository.getSakeShops()

        when (result) {
            is Resource.Result.Failure -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasFailed = true
                    )
                }
            }

            is Resource.Result.Success -> {
                val sakeShops = result.data
                _uiState.update {
                    it.copy(
                        sakeShops = sakeShops,
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class ListUiState(
    val sakeShops: List<SakeShop>,
    val isLoading: Boolean,
    val hasFailed: Boolean = false
)
