package com.jetbrains.vini.domain

data class SakeShop(
    val id: Int,
    val name: String,
    val description: String,
    val picture: String? = null,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    val googleMapsLink: String,
    val website: String
)