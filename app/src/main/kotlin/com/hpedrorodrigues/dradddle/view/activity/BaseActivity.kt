package com.hpedrorodrigues.dradddle.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.dagger.DradddleComponent

public abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun injectMembers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()
    }

    protected fun dribbbleComponent(): DradddleComponent {
        return (getApplication() as DradddleApplication).component()
    }
}