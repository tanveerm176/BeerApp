package com.example.beerapp.data.remote

/**
* Data Transfer Object: object that represents our network model
* Take the JSON code from API and parse into this data class
 * Corresponds to network model
 * */
data class BeerDto (
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String? //some images may not be available
)