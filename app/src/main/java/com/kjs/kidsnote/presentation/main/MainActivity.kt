package com.kjs.kidsnote.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kjs.kidsnote.R
import com.kjs.kidsnote.presentation.common.CommonActivity

class MainActivity : CommonActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}