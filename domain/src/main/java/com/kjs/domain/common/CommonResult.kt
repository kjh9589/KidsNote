package com.kjs.domain.common

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

enum class CommonResultState(val code: Int) {
    SUCCESS(200),
    FAILURE(-1),
    LOADING(0),
    UNKNOWN(400)
}

sealed class CommonResult<out T> {
    data class Success<out R>(val data: R): CommonResult<R>()
    data class Failure(val errorCode: CommonResultState = CommonResultState.FAILURE, val exception: Exception? = null): CommonResult<Nothing>()
    //    data class Error(val exception: Exception): CommonResult<Nothing>()
    object Loading: CommonResult<Nothing>()
    object None: CommonResult<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[errorCode=$errorCode, exception=$exception"
            is Loading -> "Loading"
            is None -> "None"
        }
    }
}

val CommonResult<*>.succeeded
    get() = this is CommonResult.Success && data != null

fun <R> CommonResult<R>.successOr(fallback: R): R {
    return (this as? CommonResult.Success<R>)?.data ?: fallback
}

val <R> CommonResult<R>.data: R?
    get() = (this as? CommonResult.Success)?.data

/**
 * Updates value of [liveData] if [Result] is of type [Success]
 */
@JvmName("updateOnSuccessT")
inline fun <reified T> CommonResult<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is CommonResult.Success) {
        liveData.value = data
    }
}
/**
 * Updates value of [MutableStateFlow] if [Result] is of type [Success]
 */
inline fun <reified T> CommonResult<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is CommonResult.Success) {
        stateFlow.value = data
    }
}