package com.hpedrorodrigues.dradddle.dagger

import dagger.Component
import javax.inject.Singleton

import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.view.activity.MainActivity
import com.hpedrorodrigues.dradddle.view.adapter.HomePagerAdapter
import com.hpedrorodrigues.dradddle.view.adapter.PopularShotsAdapter

Singleton
Component(modules = arrayOf(DradddleModule::class))
public interface DradddleComponent {

    public fun inject(application: DradddleApplication)
    public fun inject(mainActivity: MainActivity)
}