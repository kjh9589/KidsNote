package com.kjs.data.util

import com.kjs.data.common.CommonApiResult
import com.kjs.domain.common.CommonResultState
import retrofit2.Response

fun <T> Response<T>.convertCommonApiResult(): CommonApiResult<T> {
    return if (this.isSuccessful) {
        CommonApiResult.Success(this.body())
    } else {
        CommonApiResult.Failure(CommonResultState.UNKNOWN, "UNKNOWN")
    }
}