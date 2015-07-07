package com.hpedrorodrigues.dradddle.util

import android.os.Build

public object DeviceInfo {

    val DETAILS = getDetails()

    private fun getDetails(): String {
        return "\n\n\n---------------------------------------------\n" +
                "Device details:\n\n" +
                "Device: ${Build.DEVICE}\n" +
                "CPU: ${Build.SUPPORTED_ABIS[0]}\n" +
                "Manufacturer: ${Build.MANUFACTURER}\n" +
                "Model: ${Build.MODEL}\n" +
                "Hardware: ${Build.HARDWARE}\n" +
                "Android version: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
    }
}