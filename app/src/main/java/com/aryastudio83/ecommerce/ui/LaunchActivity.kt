package com.aryastudio83.ecommerce.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aryastudio83.ecommerce.R

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        setLaunchScreenTimeOut()
    }
    private fun setLaunchScreenTimeOut() {
        Looper.myLooper()?.let {
            Handler(it).postDelayed({startPreferredActivity()}, TIME_OUT)
        }
    }

    private fun startPreferredActivity() {
        launchHome(this)
        finish()
    }

    companion object {
        private const val TIME_OUT: Long = 1500
    }
}