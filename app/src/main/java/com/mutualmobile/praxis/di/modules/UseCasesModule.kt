package com.mutualmobile.praxis.di.modules

import com.mutualmobile.praxis.data.JokesRepository
import com.mutualmobile.praxis.usecases.GetJokesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    internal fun providesGetJokeUseCase(jokesRepository: JokesRepository): GetJokesUseCase{
        return GetJokesUseCase(jokesRepository)
    }

}