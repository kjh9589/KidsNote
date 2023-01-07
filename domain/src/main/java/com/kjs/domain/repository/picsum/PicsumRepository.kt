package com.kjs.domain.repository.picsum

import com.kjs.domain.common.CommonResult
import com.kjs.model.picsum.PicsumModel
import kotlinx.coroutines.flow.Flow

interface PicsumRepository {
    fun getPicsumImageList(offset: Int): Flow<CommonResult<List<PicsumModel>>>
}