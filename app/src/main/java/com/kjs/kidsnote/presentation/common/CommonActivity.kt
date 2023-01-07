package com.kjs.kidsnote.presentation.common

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class CommonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("KidsNote", "----------CREATE ACTIVITY : \" + ${this::class.simpleName} + \"----------")
    }

    open fun initViews() {}
    open fun bindViews() {}
}