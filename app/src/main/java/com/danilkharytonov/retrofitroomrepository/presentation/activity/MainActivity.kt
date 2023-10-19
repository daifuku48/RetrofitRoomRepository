package com.danilkharytonov.retrofitroomrepository.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilkharytonov.retrofitroomrepository.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        const val USER_ID = "USER_ID"
    }
}