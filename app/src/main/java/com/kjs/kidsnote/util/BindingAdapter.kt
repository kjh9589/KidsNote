package com.kjs.kidsnote.util

import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kjs.kidsnote.R
import com.kjs.kidsnote.components.StateResImageView

@BindingAdapter("loadImage")
fun ImageFilterView.loadImage(uri: String) {
    Glide.with(this)
        .load(uri)
        .placeholder(R.drawable.ic_placeholder_image)
        .into(this)
}

@BindingAdapter("isLike")
fun StateResImageView.isLike(isLike: Boolean) {
    if (isLike) {
        this.active()
    } else {
        this.inactive()
    }
}