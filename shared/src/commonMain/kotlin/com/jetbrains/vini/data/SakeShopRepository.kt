package com.jetbrains.vini.data

import com.jetbrains.vini.core.Resource
import com.jetbrains.vini.core.mapError
import com.jetbrains.vini.core.mapSuccess
import com.jetbrains.vini.domain.SakeShop
import com.jetbrains.vini.domain.SakeShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class SakeShopRepositoryImpl(
    private val sakeShopApi: SakeShopApi,
) : SakeShopRepository {
    private val shops: MutableStateFlow<List<SakeShop>> = MutableStateFlow(emptyList())

    override suspend fun getSakeShops(): Resource.Result<List<SakeShop>, String> {
        if (shops.value.isNotEmpty()) return Resource.Result.Success(shops.value)
        return sakeShopApi.getData()
            .mapSuccess { data ->
                val result = data.mapIndexed { index, sakeShopResponse ->
                    sakeShopResponse.toModel(index)
                }
                shops.update { result }
                result
            }
            .mapError { it.message ?: "Unknown error" }
    }

    override fun getSakeShop(id: Int): Resource.Result<SakeShop, String> {
       if (shops.value.isNotEmpty()) return Resource.Result.Success(shops.value.first { it.id == id })
        return Resource.Result.Failure("Not found")
    }
}
