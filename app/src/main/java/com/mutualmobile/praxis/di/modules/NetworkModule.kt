package com.mutualmobile.praxis.di.modules

import androidx.databinding.library.BuildConfig
import com.mutualmobile.praxis.BaseApp
import com.mutualmobile.praxis.di.qualifiers.Coroutine
import com.mutualmobile.praxis.di.qualifiers.RxJava
import com.mutualmobile.praxis.framework.CoroutineApiService
import com.mutualmobile.praxis.framework.RxApiService
import com.mutualmobile.praxis.repo.JokeRepo
import com.mutualmobile.praxis.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideRxSchedulers(): IRxSchedulers {
        return object : IRxSchedulers {
            override fun main() = AndroidSchedulers.mainThread()
            override fun io() = Schedulers.io()
        }
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.interceptors()
                .add(httpLoggingInterceptor)
        }
        return httpBuilder.build()
    }

    @Coroutine
    @Provides
    @Singleton
    internal fun provideCoroutineRestAdapter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseApp.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideCoroutineApiService(@Coroutine restAdapter: Retrofit): CoroutineApiService {
        return restAdapter.create(CoroutineApiService::class.java)
    }

    @RxJava
    @Provides
    @Singleton
    internal fun provideRxRestAdapter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseApp.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRxApiService(@RxJava restAdapter: Retrofit): RxApiService {
        return restAdapter.create(RxApiService::class.java)
    }

}