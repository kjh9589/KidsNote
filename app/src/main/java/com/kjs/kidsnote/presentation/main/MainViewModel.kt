package com.kjs.kidsnote.presentation.main

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
class MainViewModel @Inject constructor(
    private val getPicsumImageListUseCase: GetPicsumImageListUseCase,
    private val insertLikeUseCase: InsertLikeUseCase,
    private val getLikeUseCase: GetLikeUseCase
) : ViewModel() {
    private var imageList = mutableListOf<PicsumModel>()

    private val _picsumImageListStateFlow = MutableStateFlow<List<PicsumModel>>(emptyList())
    val picsumImageListStateFlow: StateFlow<List<PicsumModel>>
        get() = _picsumImageListStateFlow

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private var offset = 1

    fun getList() {
        viewModelScope.launch {
            getPicsumImageListUseCase.invoke(offset).collectLatest { result ->
                when (result) {
                    is CommonResult.Loading -> {
                        _loading.value = true
                    }
                    is CommonResult.Success -> {
                        if (result.data.isEmpty()) {
                            _loading.value = false
                            return@collectLatest
                        }

                        val list = result.data.map {
                            it.copy(
                                isLike = getLikeUseCase.invoke(it.id).getOrDefault(false)
                            )
                        }
                        imageList.addAll(list)

                        _picsumImageListStateFlow.value = imageList.map { it }
                        offset += 1
                        _loading.value = false

                    }
                    is CommonResult.Failure -> {
                        _loading.value = false
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

    fun reloadLike() {
        viewModelScope.launch {
            val list = imageList.map {
                it.copy(
                    isLike = getLikeUseCase.invoke(it.id).getOrDefault(false)
                )
            }

            imageList = list.toMutableList()

            _picsumImageListStateFlow.value = imageList
        }
    }

    fun clearHolder() {
        _loading.value = false
    }
}