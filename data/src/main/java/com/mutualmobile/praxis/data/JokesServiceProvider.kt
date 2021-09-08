package com.mutualmobile.praxis.data

import com.mutualmobile.praxis.domain.JokesResponse
import com.mutualmobile.praxis.domain.NetworkResult

interface JokesServiceProvider {
    suspend fun getFiveRandomJokes(): NetworkResult<JokesResponse?>
}