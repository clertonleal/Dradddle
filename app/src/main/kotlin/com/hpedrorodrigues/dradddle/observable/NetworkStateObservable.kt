package com.hpedrorodrigues.dradddle.observable

import java.util.Observable

public object NetworkStateObservable : Observable() {

    public fun stateConnectionChanged() {
        setChanged()
        notifyObservers()
    }
}