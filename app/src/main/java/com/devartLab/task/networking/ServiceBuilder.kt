package com.devartLab.task.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceBuilder {

    fun makeRetrofitService(): ConnectionEndPoint {
        return Retrofit.Builder()
            .baseUrl("http://script.googleusercontent.com/macros/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ConnectionEndPoint::class.java)
    }
}