package com.example.tfg.Data

import com.example.tfg.Data.Model.jugadoresActivos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService_JA {

    @GET("v3/nba/scores/json/Players")
    suspend fun activePlayers( @Query("key") key : String) : jugadoresActivos

    object RetrofitServiceFactory {
        fun makeRetrofitService(): RetrofitService_JA {
            return Retrofit.Builder()
                .baseUrl("https://api.sportsdata.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService_JA::class.java)
        }
    }

}