package com.example.albertontakehomeassignment.domain.model

sealed class ResponseState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResponseState<T>(data = data)
    class Error<T>(message: String) : ResponseState<T>(message = message)
}
