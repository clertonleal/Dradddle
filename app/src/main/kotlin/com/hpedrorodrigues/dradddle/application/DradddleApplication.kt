package com.hpedrorodrigues.dradddle.application

import android.app.Application

import com.hpedrorodrigues.dradddle.dagger.DradddleComponent
import kotlin.platform.platformStatic
import kotlin.properties.Delegates

public class DradddleApplication : BaseApplication() {

    companion object {

        platformStatic public var dradddleComponent: DradddleComponent by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        dradddleComponent = buildDaggerComponent()
        dradddleComponent.inject(this)
    }
}