package com.mutualmobile.praxis.usecases

import com.mutualmobile.praxis.data.JokesRepository
import com.mutualmobile.praxis.domain.JokesResponse
import com.mutualmobile.praxis.domain.NetworkResult

class GetJokesUseCase (private val jokesRepository: JokesRepository){


    suspend operator fun invoke(): NetworkResult<JokesResponse?> = jokesRepository.getJokesFromProvider()

}