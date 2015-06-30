package com.hpedrorodrigues.dradddle.view.fragment.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.dagger.DradddleComponent

public abstract class BaseFragment : Fragment() {

    protected abstract fun injectMembers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()
    }

    protected fun dradddleComponent(): DradddleComponent {
        return DradddleApplication.component()
    }
}