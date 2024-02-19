package com.gowittgroup.kredivoassignment.domain.models

sealed class Response<out T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error(val msg: String) : Response<Nothing>()
}