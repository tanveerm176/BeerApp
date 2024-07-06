package com.example.beerapp.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.beerapp.data.local.BeerDatabase
import com.example.beerapp.data.local.BeerEntity
import com.example.beerapp.data.mappers.toBeerEntity
import java.io.IOException

/**
 * Controls paging logic - puts loaded items from remote API to local database and forwards just
 * the page we need to load
 *
 * @param beerDb - local Beer data source
 * @param beerApi - remote Beer data source
 */
@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDb: BeerDatabase,
    private val beerApi: BeerApi
) : RemoteMediator<Int, BeerEntity>() {

    /**
     * Called when some form of loading in regards to pagination. Load Types:
     * 1. Refresh - entire list is refreshed
     * 2. Appending - loading next number of items
     */
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {

            /*loadKey: Int for current page we want to load*/
            val loadKey = when(loadType) {
                //start from first page
                LoadType.REFRESH -> 1

                //don't support prepend
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                //need to calculate the next page of items to load
                LoadType.APPEND -> {
                    //get ref to last item in current list
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        1 //must be first call to append if lastItem == null
                    }
                    else{
                        //ids are ascending and in order
                        /*get index of current last item in page divide by number of pages and
                        return the page after the current last item*/
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            /*make API call passing in loadKey and pageCount, returns list of beers*/
            val beers = beerApi.getBeers(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            /*take list of beers and insert into local db for caching
            * .withTransaction used to ensure all SQL calls below execute iff they all succeed*/
            beerDb.withTransaction {
                /*if refreshing list, clear the cache to be able to reinsert starting entries*/
                if (loadType == LoadType.REFRESH){
                    beerDb.dao.clearAll()
                }

                val beerEntities = beers.map { it.toBeerEntity() }
                beerDb.dao.upsertAll(beerEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        }

        catch (e: IOException){
            return MediatorResult.Error(e)
        }

        catch (e: HttpException){
            return MediatorResult.Error(e)
        }
    }
}