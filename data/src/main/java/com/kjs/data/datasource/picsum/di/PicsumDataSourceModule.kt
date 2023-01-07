package com.kjs.data.datasource.picsum.di

import com.kjs.data.api.PicsumService
import com.kjs.data.datasource.picsum.impl.PicsumDataSourceImpl
import com.kjs.data.datasource.picsum.interfaces.PicsumDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PicsumDataSourceModule {
    @Provides
    fun providePicsumDataSource(
        picsumService: PicsumService
    ): PicsumDataSource {
        return PicsumDataSourceImpl(picsumService)
    }
}