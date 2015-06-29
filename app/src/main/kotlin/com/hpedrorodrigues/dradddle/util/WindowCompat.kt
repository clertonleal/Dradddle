package com.hpedrorodrigues.dradddle.util

import android.annotation.TargetApi
import android.os.Build
import android.view.Window

public object WindowCompat {

    TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public fun setStatusBarcolor(window: Window, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color)
        }
    }
}