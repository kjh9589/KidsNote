package com.kjs.data.repository_impl.db.di

import com.kjs.data.datasource.db.interfaces.DatabaseDataSource
import com.kjs.data.repository_impl.db.DatabaseRepositoryImpl
import com.kjs.domain.repository.db.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseRepositoryModule {
    @Provides
    fun provideDatabaseRepository(
        databaseDataSource: DatabaseDataSource
    ): DatabaseRepository {
        return DatabaseRepositoryImpl(databaseDataSource)
    }
}