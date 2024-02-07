package com.example.myntradummy.data

import com.example.myntradummy.data.model.MyntraSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MyntraClient {

    @GET("gateway/v2/search/men/")
    suspend fun getProductsList() : MyntraSearchResponse
}
