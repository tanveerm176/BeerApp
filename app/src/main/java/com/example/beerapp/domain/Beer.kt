package com.example.beerapp.domain

/**
 * Domain level class - common class for entire project. Single source of truth
 */
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String?
)
