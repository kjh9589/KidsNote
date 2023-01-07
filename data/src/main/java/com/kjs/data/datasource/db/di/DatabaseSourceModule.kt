package com.kjs.data.datasource.db.di

import com.kjs.data.datasource.db.impl.DatabaseDataSourceImpl
import com.kjs.data.datasource.db.interfaces.DatabaseDataSource
import com.kjs.data.db.picsum.PicsumDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseSourceModule {
    @Provides
    fun provideDatabaseDataSource(
        dao: PicsumDao
    ): DatabaseDataSource {
        return DatabaseDataSourceImpl(dao)
    }
}