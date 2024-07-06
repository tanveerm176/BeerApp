package com.example.beerapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


/**
 * Data Access Object - define queries for the database
 */
@Dao
interface BeerDao {
    /**
     * pass a list of BeerEntity and insert all into the database
     * @param beers a list of BeerEntity
     */
    @Upsert
    suspend fun upsertAll(beers: List<BeerEntity>)

    /**
     * Function to get the paging source from the BeerDao -
     * Implemented via Room library
     * @return map of single BeerEntity and page number it belongs to
     */
    @Query("SELECT * FROM beerentity")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    /**
     * clears cache from database
     */
    @Query("DELETE FROM beerentity")
    suspend fun clearAll()
}