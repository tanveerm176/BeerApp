package com.example.beerapp.data.mappers

import com.example.beerapp.data.local.BeerEntity
import com.example.beerapp.data.remote.BeerDto
import com.example.beerapp.domain.Beer

/**
 * Map Data Transfer Object to Entity in local database for caching
 */
fun BeerDto.toBeerEntity() : BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

/**
 * Map Entity in local database to domain level class, to establish single source of truth.
 */
fun BeerEntity.toBeer() : Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}