package com.jetbrains.vini.domain

import com.jetbrains.vini.core.Resource
import com.jetbrains.vini.data.SakeShopResponse

interface SakeShopRepository {
    suspend fun getSakeShops(): Resource.Result<List<SakeShop>, String>
    fun getSakeShop(id: Int): Resource.Result<SakeShop, String>
}