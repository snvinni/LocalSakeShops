package com.jetbrains.vini

import com.jetbrains.vini.core.Resource
import com.jetbrains.vini.domain.SakeShop
import com.jetbrains.vini.domain.SakeShopRepository

class FakeSakeShopRepository(private val responseType: ResponseType = ResponseType.Success) :
    SakeShopRepository {
    override suspend fun getSakeShops(): Resource.Result<List<SakeShop>, String> {
        return when (responseType) {
            ResponseType.Failure -> Resource.Result.Failure("Error")

            ResponseType.Success -> Resource.Result.Success(
                listOf(
                    SakeShop(
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
                )
            )

        }
    }

    override fun getSakeShop(id: Int): Resource.Result<SakeShop, String> {
        return when (responseType) {
            ResponseType.Failure -> Resource.Result.Failure("Error")
            ResponseType.Success -> Resource.Result.Success(
                SakeShop(
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
            )
        }
    }

    sealed interface ResponseType {
        object Success : ResponseType
        object Failure : ResponseType
    }
}