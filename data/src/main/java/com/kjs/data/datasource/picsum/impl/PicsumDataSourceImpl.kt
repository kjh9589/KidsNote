package com.kjs.data.datasource.picsum.impl

import com.kjs.data.api.PicsumService
import com.kjs.data.common.CommonApiResult
import com.kjs.data.datasource.picsum.interfaces.PicsumDataSource
import com.kjs.data.response.picsum.PicsumListResponse
import com.kjs.data.util.convertCommonApiResult
import javax.inject.Inject

class PicsumDataSourceImpl @Inject constructor(
    private val api: PicsumService
): PicsumDataSource {
    override suspend fun getPicsumImageList(offset: Int): CommonApiResult<PicsumListResponse> {
        return api.getPicsumList(offset).convertCommonApiResult()
    }
}