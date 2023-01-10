package com.kjs.domain.usecase.db

import com.kjs.domain.common.SuspendUseCase
import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.db.DatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLikeUseCase @Inject constructor(
    private val repository: DatabaseRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<String, Boolean>(dispatcher) {
    override suspend fun execute(parameters: String): Boolean {
        return repository.getPicsumLike(parameters)
    }
}