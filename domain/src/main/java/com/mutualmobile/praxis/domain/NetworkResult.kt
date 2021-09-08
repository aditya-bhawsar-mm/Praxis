package com.mutualmobile.praxis.domain

sealed class NetworkResult<T>(val data: T? =null, val message:String? =null) {
    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(msg: String,data: T?= null): NetworkResult<T>(data,msg)
}