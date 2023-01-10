package com.kjs.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameter: P): Flow<CommonResult<R>> = execute(parameter)
        .catch { e -> emit(CommonResult.Failure(exception = Exception(e)))}
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameter: P): Flow<CommonResult<R>>
}