package com.kjs.domain.usecase.db

import com.kjs.domain.common.SuspendUseCase
import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.db.DatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertLikeUseCase @Inject constructor(
    private val repository: DatabaseRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<InsertLikeUseCase.Params, Unit>(dispatcher){
    override suspend fun execute(parameters: Params) {
        return repository.insertLike(parameters.id, parameters.isLike)
    }

    data class Params(
        val id: String,
        val isLike: Boolean
    )
}