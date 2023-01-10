package com.kjs.data.common

import com.kjs.domain.common.CommonResultState

sealed class CommonApiResult<out T>(
    val status: CommonResultState,
    val data: T?,
    val message: String?
) {
    data class Success<out R>(
        val successData: R?,
    ) : CommonApiResult<R>(
        status = CommonResultState.SUCCESS,
        data = successData,
        message = null
    )

    data class Failure(
        val failureStatus: CommonResultState,
        val failureMessage: String
    ) : CommonApiResult<Nothing>(
        status = failureStatus,
        data = null,
        message = failureMessage
    )

    object Loading : CommonApiResult<Nothing>(
        status = CommonResultState.LOADING,
        data = null,
        message = null
    )


}