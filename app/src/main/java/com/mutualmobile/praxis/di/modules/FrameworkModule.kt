package com.mutualmobile.praxis.di.modules

import com.mutualmobile.praxis.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    @Singleton
    @Provides
    internal fun

}