package com.example.albertontakehomeassignment.domain.repository

import com.example.albertontakehomeassignment.domain.api.AcromineService

class AcromineRepository(
    private val acromineService: AcromineService
) {
    suspend fun getAcromineWithQuery(query: String?) =
        query?.let {
            acromineService.getAcromine(it)
        }
}
