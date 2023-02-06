package com.example.albertontakehomeassignment.domain.api

import com.example.albertontakehomeassignment.domain.model.AcromineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineService {
    @GET("/software/acromine/dictionary.py")
    suspend fun getAcromine(
        @Query("sf") sf: String
    ): AcromineResponse
}
