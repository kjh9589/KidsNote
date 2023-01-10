package com.kjs.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object PicsumApiModule {
    @Provides
    fun providePicsumService(
        retrofit: Retrofit
    ): PicsumService {
        return retrofit.create(PicsumService::class.java)
    }
}