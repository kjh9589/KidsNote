package com.kjs.data.datasource.picsum.interfaces

import com.kjs.data.common.CommonApiResult
import com.kjs.data.response.picsum.PicsumListResponse

interface PicsumDataSource {
    suspend fun getPicsumImageList(
        offset: Int
    ): CommonApiResult<PicsumListResponse>
}