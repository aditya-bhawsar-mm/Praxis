package com.mutualmobile.praxis.di.modules

import com.mutualmobile.praxis.data.JokesRepository
import com.mutualmobile.praxis.data.JokesServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    internal fun provideJokesRepository(serviceProvider: JokesServiceProvider): JokesRepository {
        return JokesRepository(serviceProvider)
    }

}