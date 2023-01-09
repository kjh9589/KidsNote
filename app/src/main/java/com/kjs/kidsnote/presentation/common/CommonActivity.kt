package com.kjs.kidsnote.presentation.common

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kjs.kidsnote.presentation.dialog.DialogLoading

open class CommonActivity: AppCompatActivity() {
    private var dialogLoading: DialogLoading? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("KidsNote", "----------CREATE ACTIVITY : \" + ${this::class.simpleName} + \"----------")
    }

    open fun initViews() {}
    open fun bindViews() {}

    fun showLoadingDialog() {
        dialogLoading = DialogLoading()
        dialogLoading?.show(
            supportFragmentManager, DialogLoading.TAG
        )
    }

    fun hideLoadingDialog() {
        if (dialogLoading == null) {
            return
        }

        dialogLoading?.dismiss()
        dialogLoading = null
    }

}