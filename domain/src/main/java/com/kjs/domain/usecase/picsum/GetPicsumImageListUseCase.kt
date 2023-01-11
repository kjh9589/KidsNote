package com.kjs.domain.usecase.picsum

import com.kjs.domain.common.CommonResult
import com.kjs.domain.common.FlowUseCase
import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.picsum.PicsumRepository
import com.kjs.model.picsum.PicsumModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPicsumImageListUseCase @Inject constructor(
    private val repository: PicsumRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<Int, List<PicsumModel>>(dispatcher){
    override fun execute(parameter: Int): Flow<CommonResult<List<PicsumModel>>> {
        return repository.getPicsumImageList(parameter)
    }
}