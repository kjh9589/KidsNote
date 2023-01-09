package com.kjs.data.repository_impl.db

import com.kjs.data.datasource.db.interfaces.DatabaseDataSource
import com.kjs.domain.repository.db.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val datasource: DatabaseDataSource
): DatabaseRepository {
    override suspend fun insertLike(id: String, isLike: Boolean) {
        datasource.insertLike(id, isLike)
    }

    override suspend fun getPicsumLike(id: String): Boolean {
        return datasource.getPicsumLike(id)
    }
}