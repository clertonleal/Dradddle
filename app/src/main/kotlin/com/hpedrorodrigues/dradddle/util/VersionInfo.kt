package com.hpedrorodrigues.dradddle.util

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.hpedrorodrigues.dradddle.application.DradddleApplication

import javax.inject.Inject

public object VersionInfo {

    private val TAG = javaClass<VersionInfo>().getSimpleName()

    var context: Context? = null
        @Inject set

    init {
        DradddleApplication.component().inject(this)
    }

    public fun getVersionName(): String {
        var version = "-"
        try {
            version = context!!.getPackageManager()
                    .getPackageInfo(context!!.getPackageName(), 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Error to get package ", e)
        }
        return version
    }

    public fun getVersionCode(): Int {
        var version = 0
        try {
            version = context!!.getPackageManager()
                    .getPackageInfo(context!!.getPackageName(), 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Error to get package ", e)
        }
        return version
    }
}