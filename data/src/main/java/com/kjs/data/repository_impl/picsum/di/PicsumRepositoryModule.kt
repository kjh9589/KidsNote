package com.kjs.data.repository_impl.picsum.di

import com.kjs.data.datasource.picsum.interfaces.PicsumDataSource
import com.kjs.data.repository_impl.picsum.PicsumRepositoryImpl
import com.kjs.domain.repository.picsum.PicsumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PicsumRepositoryModule {
    @Provides
    fun providePicsumRepository(
        picsumDataSource: PicsumDataSource
    ): PicsumRepository {
        return PicsumRepositoryImpl(picsumDataSource)
    }
}