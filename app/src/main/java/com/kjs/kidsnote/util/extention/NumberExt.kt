package com.kjs.kidsnote.util.extention

import android.content.res.Resources

val Int.getIntDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.getFloatDp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)

val Float.getIntDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.getFloatDp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)