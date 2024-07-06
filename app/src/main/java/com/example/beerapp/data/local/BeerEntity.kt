package com.example.beerapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Database entity for local database - corresponds to database model
 */
@Entity
data class BeerEntity(
    @PrimaryKey
    val id: Int, //id is Primary Key in the local Room database
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String?
)