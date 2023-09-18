package com.rig.apisearch.di

import com.rig.apisearch.Repository
import com.rig.apisearch.services.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

//    https://api.artic.edu/api/v1/artworks/search?q=cats
    val baseUrl = "https://api.artic.edu/"
    @Provides
    fun providesRetrofitBuilder():Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    fun providesApiService(retrofit:Retrofit):ApiInterface
    = retrofit.create(ApiInterface::class.java)

    @Provides
    fun providesRepository(apiService: ApiInterface) = Repository(apiService)


}