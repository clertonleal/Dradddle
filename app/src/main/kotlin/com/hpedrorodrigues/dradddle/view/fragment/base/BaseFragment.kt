package com.hpedrorodrigues.dradddle.view.fragment.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.dagger.DradddleComponent
import rx.subscriptions.CompositeSubscription

public abstract class BaseFragment : Fragment() {

    protected var compositeSubscription: CompositeSubscription = CompositeSubscription()

    protected abstract fun injectMembers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()
    }

    override fun onDestroy() {
        unsubscribeSubscriptions()
        super.onDestroy()
    }

    protected fun unsubscribeSubscriptions() {
        compositeSubscription.unsubscribe()
        compositeSubscription = CompositeSubscription()
    }

    protected fun dradddleComponent(): DradddleComponent {
        return DradddleApplication.component()
    }
}