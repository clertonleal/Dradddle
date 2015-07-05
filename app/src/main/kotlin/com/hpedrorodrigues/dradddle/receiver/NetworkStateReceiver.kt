package com.hpedrorodrigues.dradddle.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hpedrorodrigues.dradddle.observable.NetworkStateObservable

public class NetworkStateReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NetworkStateObservable.stateConnectionChanged()
    }
}