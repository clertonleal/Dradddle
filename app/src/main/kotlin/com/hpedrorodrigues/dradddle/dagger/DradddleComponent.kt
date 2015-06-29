package com.hpedrorodrigues.dradddle.dagger

import dagger.Component
import javax.inject.Singleton

import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.view.activity.AboutActivity
import com.hpedrorodrigues.dradddle.view.activity.MainActivity
import com.hpedrorodrigues.dradddle.view.activity.ProfileActivity
import com.hpedrorodrigues.dradddle.view.activity.SettingsActivity

Singleton
Component(modules = arrayOf(DradddleModule::class))
public interface DradddleComponent {

    public fun inject(application: DradddleApplication)
    public fun inject(mainActivity: MainActivity)
    public fun inject(aboutActivity: AboutActivity)
    public fun inject(profileActivity: ProfileActivity)
    public fun inject(settingsActivity: SettingsActivity)
}