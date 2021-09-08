package com.mutualmobile.praxis.data

import com.mutualmobile.praxis.domain.JokesResponse
import com.mutualmobile.praxis.domain.NetworkResult

class JokesRepository (private val jokesServiceProvider: JokesServiceProvider) {

    suspend fun getJokesFromProvider() : NetworkResult<JokesResponse?> =
            jokesServiceProvider.getFiveRandomJokes()

}