package com.jetbrains.vini.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.vini.core.Resource
import com.jetbrains.vini.usecase.LaunchUrlUseCase
import com.jetbrains.vini.domain.SakeShop
import com.jetbrains.vini.domain.SakeShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val sakeShopRepository: SakeShopRepository,
    private val shopId: Int,
    private val launchUrlUseCase: LaunchUrlUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        DetailUiState(
            null,
            true
        )
    )

    val uiState = _uiState.onStart {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        getSakeShop(shopId)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        DetailUiState(null, true)
    )

    private fun getSakeShop(id: Int) {
        val result = sakeShopRepository.getSakeShop(id)
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
                _uiState.update {
                    it.copy(
                        sakeShop = result.data,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun launchUrl(url: String) = viewModelScope.launch {
        launchUrlUseCase(url)
    }
}

data class DetailUiState(
    val sakeShop: SakeShop?,
    val isLoading: Boolean,
    val hasFailed: Boolean = false
)