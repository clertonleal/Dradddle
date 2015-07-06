package com.hpedrorodrigues.dradddle.util

import android.content.Context
import android.preference.PreferenceManager
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import javax.inject.Inject

public object DradddlePreferences {

    var context: Context? = null
        @Inject set

    init {
        DradddleApplication.component().inject(this)
    }

    public fun getBoolean(name: String): Boolean {

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(name, false)
    }

    public fun putBoolean(name: String, value: Boolean) {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().putBoolean(name, value).apply()
    }
}