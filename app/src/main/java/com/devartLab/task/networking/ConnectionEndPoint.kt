package com.devartLab.task.networking

import com.devartLab.task.model.HomeResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionEndPoint {

    @GET("echo")
    suspend fun requestHomeData(@Query("user_content_key")user_content_key:String
                                ,@Query("lib")lib:String) : Response<List<HomeResponseModel>>
}