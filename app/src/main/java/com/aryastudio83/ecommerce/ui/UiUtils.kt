package com.aryastudio83.ecommerce.ui

import android.content.Context
import android.content.Intent
import com.aryastudio83.ecommerce.ui.home.MainActivity

internal fun launchHome(context: Context) {
    val homeIntent = Intent(context, MainActivity::class.java)
    context.startActivity(homeIntent)
}