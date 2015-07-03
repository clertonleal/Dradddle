package com.hpedrorodrigues.dradddle.service

import android.net.ConnectivityManager
import javax.inject.Inject

public class ConnectionService {

    @Inject
    constructor() {}

    var connectivityManager: ConnectivityManager? = null
        @Inject set

    public fun hasConnection(): Boolean {
        val networkInfo = connectivityManager!!.getActiveNetworkInfo()
        return networkInfo != null && networkInfo.isConnectedOrConnecting()
    }
}