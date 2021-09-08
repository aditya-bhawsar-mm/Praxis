package com.mutualmobile.praxis.framework

import com.mutualmobile.praxis.data.JokesServiceProvider
import com.mutualmobile.praxis.domain.JokesResponse
import com.mutualmobile.praxis.domain.NetworkResult
import java.io.IOException

class CoroutineInteraction(
    private val apiService: CoroutineApiService
) : JokesServiceProvider
{
    override suspend fun getFiveRandomJokes(): NetworkResult<JokesResponse?> {
        lateinit var networkResult :NetworkResult<JokesResponse?>
        try {
            val response = apiService.getFiveRandomJokes()
            return if(response.isSuccessful){
                networkResult = NetworkResult.Success(data = response.body())
                networkResult
            }else{
                networkResult = NetworkResult.Error(msg = "Request Failed")
                networkResult
            }
        }catch (e: Exception){
            networkResult = when(e){
                is IOException -> NetworkResult.Error(msg = "Network Issue")
                else -> NetworkResult.Error(msg = "Something Went Wrong")
            }
            return networkResult
        }
    }
}