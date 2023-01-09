package com.kjs.kidsnote.presentation.main.detail_picsum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjs.domain.common.CommonResult
import com.kjs.domain.usecase.db.GetLikeUseCase
import com.kjs.domain.usecase.db.InsertLikeUseCase
import com.kjs.domain.usecase.picsum.GetPicsumImageListUseCase
import com.kjs.model.picsum.PicsumModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPicsumViewModel @Inject constructor(
    private val getPicsumImageListUseCase: GetPicsumImageListUseCase,
    private val insertLikeUseCase: InsertLikeUseCase,
    private val getLikeUseCase: GetLikeUseCase
) : ViewModel() {
    private var imageList = mutableListOf<PicsumModel>()

    private val _picsumImageListStateFlow = MutableStateFlow<List<PicsumModel>>(emptyList())
    val picsumImageListStateFlow: StateFlow<List<PicsumModel>>
        get() = _picsumImageListStateFlow

    private var prevOffset = 0
    private var nextOffset = 0
    var isInit = false

    var lock = false

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getInitList(offset: Int) {
        prevOffset = offset - 1
        nextOffset = offset

        viewModelScope.launch {
            lock = true
            getPicsumImageListUseCase.invoke(offset).collectLatest { result ->
                when (result) {
                    is CommonResult.Loading -> {
                        _loading.value = true
                    }
                    is CommonResult.Success -> {
                        val list = result.data.map {
                            it.copy(
                                isLike = getLikeUseCase.invoke(it.id).getOrDefault(false)
                            )
                        }
                        imageList.addAll(list)

                        _picsumImageListStateFlow.value = imageList.map { it }
                        nextOffset += 1

                        _loading.value = false
                    }
                    is CommonResult.Failure -> {
                        _loading.value = false
                        lock = false
                    }
                }
            }
        }
    }

    fun getPrevList() {
        if (prevOffset == 0) {
            lock = false
            return
        }
        viewModelScope.launch {
            getPicsumImageListUseCase.invoke(prevOffset).collectLatest { result ->
                when (result) {
                    is CommonResult.Loading -> {
                        _loading.value = true
                    }
                    is CommonResult.Success -> {
                        if (result.data.isEmpty()) {
                            return@collectLatest
                        }

                        val list = result.data.map {
                            it.copy(
                                isLike = getLikeUseCase.invoke(it.id).getOrDefault(false)
                            )
                        }
                        imageList = (list + imageList).toMutableList()

                        _picsumImageListStateFlow.value = imageList
                        prevOffset = if (prevOffset - 1 == 0) 0 else prevOffset - 1

                        _loading.value = false

                    }
                    is CommonResult.Failure -> {
                        _loading.value = false
                        lock = false
                    }
                }
            }
        }
    }

    fun getNextList() {
        viewModelScope.launch {
            getPicsumImageListUseCase.invoke(nextOffset).collectLatest { result ->
                when (result) {
                    is CommonResult.Loading -> {
                        _loading.value = true
                    }
                    is CommonResult.Success -> {
                        if (result.data.isEmpty()) {
                            return@collectLatest
                        }

                        val list = result.data.map {
                            it.copy(
                                isLike = getLikeUseCase.invoke(it.id).getOrDefault(false)
                            )
                        }
                        imageList = (imageList + list).toMutableList()

                        _picsumImageListStateFlow.value = imageList
                        nextOffset += 1

                        _loading.value = false

                    }
                    is CommonResult.Failure -> {
                        _loading.value = false
                        lock = false
                    }
                }
            }
        }
    }

    fun like(id: String, isLike: Boolean, position: Int) {
        viewModelScope.launch {
            insertLikeUseCase.invoke(InsertLikeUseCase.Params(id, isLike))
                .onSuccess {
                    imageList[position] = imageList[position].copy(isLike = isLike)
                    _picsumImageListStateFlow.value = imageList.map { it }
                }
                .onFailure {
                }
        }
    }

    fun clearHolder() {
        _loading.value = false
    }
}