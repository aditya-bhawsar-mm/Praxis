package com.mutualmobile.praxis.domain

data class JokesResponse(
    var type: String,
    var value: List<Joke>
)