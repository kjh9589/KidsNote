package com.kjs.model.picsum

data class PicsumModel(
    val id: String = "",
    val author: String = "",
    val imageUri: String = "",
    val url: String = "",
    val isLike: Boolean = false
)
