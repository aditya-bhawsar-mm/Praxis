package com.mutualmobile.praxis.framework

import com.mutualmobile.praxis.data.JokesServiceProvider

class CoroutineInteraction(
    private val coroutineApiService: CoroutineApiService
) : JokesServiceProvider {

    override suspend fun getFiveRandomJokes(): JokeListResponse {

    }


}