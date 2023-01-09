package com.kjs.data.repository_impl.picsum

import com.kjs.data.datasource.picsum.interfaces.PicsumDataSource
import com.kjs.data.response.picsum.toPicsumModel
import com.kjs.domain.common.CommonResult
import com.kjs.domain.common.CommonResultState
import com.kjs.domain.repository.picsum.PicsumRepository
import com.kjs.model.picsum.PicsumModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PicsumRepositoryImpl @Inject constructor(
    private val dataSource: PicsumDataSource
): PicsumRepository {
    override fun getPicsumImageList(offset: Int): Flow<CommonResult<List<PicsumModel>>> {
        return flow {
            emit(CommonResult.Loading)

            val result = dataSource.getPicsumImageList(offset)

            if (result.status == CommonResultState.SUCCESS) {
                if (result.data!!.isEmpty()) {
                    return@flow
                }

                emit(
                    CommonResult.Success(
                        result.data!!.map { it.toPicsumModel() }
                    )
                )
            } else {
                emit(CommonResult.Failure(result.status, Exception("${result.message}")))
            }
        }
    }
}