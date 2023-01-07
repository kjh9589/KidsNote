package com.kjs.data.db.di

import android.content.Context
import androidx.room.Room
import com.kjs.data.db.AppDatabase
import com.kjs.data.db.picsum.PicsumDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.databaseName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePicsumDao(database: AppDatabase): PicsumDao {
        return database.picsumDao()
    }
}