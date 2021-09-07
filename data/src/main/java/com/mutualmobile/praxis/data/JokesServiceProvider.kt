package com.mutualmobile.praxis.data

interface JokesServiceProvider {
    suspend fun getFiveRandomJokes(): JokeListResponse
}