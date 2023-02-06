package com.example.albertontakehomeassignment.presentation.viewmodel

import androidx.lifecycle.*
import com.example.albertontakehomeassignment.domain.model.Lf
import com.example.albertontakehomeassignment.domain.repository.AcromineRepository
import kotlinx.coroutines.launch
import java.lang.Exception

open class AcromineViewModel(
    private val repository: AcromineRepository
) : ViewModel() {
    private val _longFormList = MutableLiveData<Result<List<Lf>>?>()

    fun observeLongFormList() = _longFormList as LiveData<Result<List<Lf>>?>

    fun getLongFormWithQuery(query: String?) {
        viewModelScope.launch {
            val result = try {
                query?.let {
                    val a = repository.getAcromineWithQuery(query)
                    println(a)
                    Result.success(repository.getAcromineWithQuery(query).first().lfs)
                }
            } catch (e: Exception) {
                Result.failure(e)
            } finally {
                Result.failure<List<Lf>>(Exception("Request Fails"))
            }
            _longFormList.postValue(result)
        }
    }
}

class AcromineViewModelFactory(private val repository: AcromineRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AcromineViewModel(repository) as T
    }
}
