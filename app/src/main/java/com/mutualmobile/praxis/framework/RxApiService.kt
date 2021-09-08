package com.mutualmobile.praxis.framework

import com.mutualmobile.praxis.domain.JokesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RxApiService {
  @GET("/jokes/random/5") fun getFiveRandomJokes(): Single<JokesResponse>
}