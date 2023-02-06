package com.example.albertontakehomeassignment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertontakehomeassignment.domain.model.AcromineResponse
import com.example.albertontakehomeassignment.domain.repository.AcromineRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class AcromineViewModel(
    private val repository: AcromineRepository
): ViewModel() {
    private val _acromineList = MutableLiveData<Result<AcromineResponse?>>()

    fun observeAcromineList() = _acromineList as LiveData<Result<AcromineResponse?>>

    fun getAcromineWithQuery(query: String?) {
        viewModelScope.launch {
            val result = try {
                Result.success(repository.getAcromineWithQuery(query))
            } catch (e: Exception) {
                Result.failure<AcromineResponse>(e)
            } finally {
                Result.failure<AcromineResponse>(
                    Exception("Request Fails")
                )
            }
            _acromineList.postValue(result)
        }
    }
}
