package com.mutualmobile.praxis.framework

import com.mutualmobile.praxis.data.JokesServiceProvider

class CoroutineInteraction(
    private val apiService: CoroutineApiService
) : JokesServiceProvider
{
    override suspend fun getFiveRandomJokes(): JokeListResponse {
        val response = apiService.getFiveRandomJokes()

    }
}