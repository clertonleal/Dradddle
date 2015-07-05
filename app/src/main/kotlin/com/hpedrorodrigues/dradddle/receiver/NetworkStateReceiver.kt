package com.hpedrorodrigues.dradddle.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

public class NetworkStateReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NetworkStateObservable.stateConnectionChanged()
    }
}

object NetworkStateObservable : Observable() {

    public fun stateConnectionChanged() {
        setChanged()
        notifyObservers()
    }
}