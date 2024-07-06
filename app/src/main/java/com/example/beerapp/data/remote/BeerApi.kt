package com.example.beerapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines different endpoints and queries to API*/
interface BeerApi {

    /**
     * Retrieves a list of beers from the API.
     *
     * This function performs a GET request to the "beers" endpoint and supports pagination.
     *
     * @param page The page number to retrieve. Used to support pagination.
     * @param pageCount The number of items to retrieve per page.
     * @return A list of [BeerDto] objects.
     */
    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int, //to be able to support pagination
        @Query("per_page") pageCount: Int //how many items to get per page
    ): List<BeerDto>


    /**
     * Define for base url
     */
    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}

