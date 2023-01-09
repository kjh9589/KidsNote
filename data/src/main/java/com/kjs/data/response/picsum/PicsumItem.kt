package com.kjs.data.response.picsum


import com.google.gson.annotations.SerializedName
import com.kjs.model.picsum.PicsumModel

data class PicsumItem(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("download_url")
    val downloadUrl: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0
)

fun PicsumItem.toPicsumModel(): PicsumModel {
    return PicsumModel(
        id = id,
        author = author,
        imageUri = downloadUrl,
        url = url,
        isLike = false
    )
}