package com.hpedrorodrigues.dradddle.application

import android.app.Application

import com.hpedrorodrigues.dradddle.dagger.DradddleComponent

public class DradddleApplication : BaseApplication() {

    private var dradddleComponent: DradddleComponent? = null

    override fun onCreate() {
        super.onCreate()
        dradddleComponent = buildDaggerComponent()
        component().inject(this)
    }

    public fun component(): DradddleComponent {
        return dradddleComponent!!
    }
}