package com.aryastudio83.ecommerce.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aryastudio83.ecommerce.R
import com.aryastudio83.ecommerce.ui.auth.AuthActivity
import com.aryastudio83.ecommerce.ui.data.EcommerceSessionManager

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
      val session = EcommerceSessionManager()
      if (session.isLoggedIn()) {
          launchHome(this)
          finish()
      } else {
          startActivity(Intent(this, AuthActivity::class.java))
          finish()
      }
    }

    companion object {
        private const val TIME_OUT: Long = 1500
    }
}