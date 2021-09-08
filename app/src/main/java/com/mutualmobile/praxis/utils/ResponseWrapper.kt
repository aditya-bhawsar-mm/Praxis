package com.mutualmobile.praxis.utils

//Response wrapping for better UI
sealed class ResponseWrapper<T>(val data: T?= null, val message:String? =null) {
    class Success<T>(data: T): ResponseWrapper<T>(data)
    class Error<T>(msg: String,data: T?= null): ResponseWrapper<T>(data,msg)
    class Loading<T>: ResponseWrapper<T>()
    class Waiting<T>: ResponseWrapper<T>()
}