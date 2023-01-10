package com.kjs.data.api

import com.kjs.data.response.picsum.PicsumListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsumService {
    @GET("v2/list")
    suspend fun getPicsumList(
        @Query("page") page: Int
    ): Response<PicsumListResponse>

}