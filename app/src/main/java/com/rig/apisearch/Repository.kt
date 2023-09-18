package com.rig.apisearch

import com.rig.apisearch.responseData.data
import com.rig.apisearch.services.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiInterface) {
//    suspend fun searchQuery(s:String):List<data>{
//       return apiService.searchParams(s)
//    }
suspend fun searchQuery(s: String): data? {
    val response = apiService.searchParams(s)
    if (response.isSuccessful) {
        return response.body()
    } else {
        // Handle error based on response code or return an empty list
        return null
    }
}

    suspend fun searchPagination(s:String, page:Int, limit:Int):data?{
        val response = apiService.searchParamsPagination(s,page,limit)
        if(response.isSuccessful){
            return response.body()
        }
        else{
            return null
        }
    }
}