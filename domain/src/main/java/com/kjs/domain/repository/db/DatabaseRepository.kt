package com.kjs.domain.repository.db

interface DatabaseRepository {
    suspend fun insertLike(id: String, isLike: Boolean)
    suspend fun getPicsumLike(id: String): Boolean
}