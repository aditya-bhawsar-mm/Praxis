package com.mutualmobile.praxis.framework

import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.model.JokeResponse
import com.mutualmobile.praxis.domain.JokesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoroutineApiService {
  @GET("/jokes/random/5") suspend fun getFiveRandomJokes(): Response<JokesResponse>
}