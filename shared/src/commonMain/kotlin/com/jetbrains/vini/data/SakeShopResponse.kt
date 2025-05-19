package com.jetbrains.vini.data

import com.jetbrains.vini.domain.SakeShop
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SakeShopResponse(
    val name: String,
    val description: String,
    val picture: String? = null,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    @SerialName("google_maps_link")
    val googleMapsLink: String,
    val website: String
)

fun SakeShopResponse.toModel(id: Int): SakeShop {
    return SakeShop(
        id = id,
        name = name,
        description = description,
        picture = picture,
        rating = rating,
        address = address,
        coordinates = coordinates,
        googleMapsLink = googleMapsLink,
        website = website
    )
}


