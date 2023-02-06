package com.example.albertontakehomeassignment.domain.repository

import com.example.albertontakehomeassignment.domain.api.AcromineService
import com.example.albertontakehomeassignment.domain.model.AcromineResponse

class AcromineRepository(
    private val acromineService: AcromineService
) {
    suspend fun getAcromineWithQuery(query: String): AcromineResponse =
        query.let {
            acromineService.getAcromine(it)
        }
}
