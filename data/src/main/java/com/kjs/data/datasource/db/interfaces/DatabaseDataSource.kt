package com.kjs.data.datasource.db.interfaces

interface DatabaseDataSource {
    suspend fun insertLike(id: String, isLike: Boolean)
    suspend fun getPicsumLike(id: String): Boolean
}