package com.example.tfg.Data

import com.example.tfg.Data.Model.jugadoresStats
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService_JS {

    @GET("v3/nba/stats/json/PlayerSeasonStats/2024")
    suspend fun statsPlayers( @Query("key") key : String) : jugadoresStats

    object RetrofitServiceFactory {
        fun makeRetrofitService(): RetrofitService_JS {
            return Retrofit.Builder()
                .baseUrl("https://api.sportsdata.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService_JS::class.java)
        }
    }

}