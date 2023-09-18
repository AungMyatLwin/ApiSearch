package com.rig.apisearch.services

import com.rig.apisearch.responseData.data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/v1/artworks/search")
    suspend fun searchParams(@Query("q") q:String): Response<data>


    @GET("/api/v1/artworks/search")
    suspend fun searchParamsPagination(@Query("q") q:String,
                             @Query("page")page:Int,
                             @Query("limit") limit:Int): Response<data>
}