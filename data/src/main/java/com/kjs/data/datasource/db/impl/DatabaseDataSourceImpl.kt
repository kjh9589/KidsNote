package com.kjs.data.datasource.db.impl

import com.kjs.data.datasource.db.interfaces.DatabaseDataSource
import com.kjs.data.db.picsum.PicsumDao
import com.kjs.data.db.picsum.PicsumEntity
import javax.inject.Inject

class DatabaseDataSourceImpl @Inject constructor(
    private val dao: PicsumDao
): DatabaseDataSource {
    override suspend fun insertLike(id: String, isLike: Boolean) {
        dao.insert(PicsumEntity(id, isLike))
    }

    override suspend fun getPicsumLike(id: String): Boolean {
        return dao.getPicsum(id) ?: false
    }
}