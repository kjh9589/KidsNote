package com.kjs.kidsnote.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kjs.kidsnote.R
import com.kjs.kidsnote.databinding.DialogLoadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogLoading: DialogFragment() {
    companion object {
        const val TAG = "DialogLoading"
    }

    private var mBinding: DialogLoadingBinding? = null
    private val binding: DialogLoadingBinding
        get() = mBinding!!

    override fun getTheme(): Int = R.style.dialogTransparentScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogLoadingBinding.inflate(inflater, container, false)

        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mBinding = null
    }
}