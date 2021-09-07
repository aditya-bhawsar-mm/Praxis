package com.mutualmobile.praxis.di.modules

import com.mutualmobile.praxis.data.JokesServiceProvider
import com.mutualmobile.praxis.framework.CoroutineApiService
import com.mutualmobile.praxis.framework.CoroutineInteraction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    @Provides
    @Singleton
    internal fun provideJokesServiceProvider(apiService: CoroutineApiService): JokesServiceProvider {
        return CoroutineInteraction(apiService)
    }

}