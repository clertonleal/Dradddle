package com.hpedrorodrigues.dradddle.dagger

import dagger.Component
import javax.inject.Singleton

import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.receiver.NetworkStateChangeReceiver
import com.hpedrorodrigues.dradddle.util.DimensionUtil
import com.hpedrorodrigues.dradddle.util.DradddlePicasso
import com.hpedrorodrigues.dradddle.util.DradddlePreferences
import com.hpedrorodrigues.dradddle.util.VersionInfo
import com.hpedrorodrigues.dradddle.view.activity.AboutActivity
import com.hpedrorodrigues.dradddle.view.activity.MainActivity
import com.hpedrorodrigues.dradddle.view.activity.DribbbleActivity
import com.hpedrorodrigues.dradddle.view.activity.SettingsActivity
import com.hpedrorodrigues.dradddle.view.adapter.ShotsFragmentPagerAdapter
import com.hpedrorodrigues.dradddle.view.fragment.shot.DebutShotsFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.PopularShotsFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.RecentShotsFragment

Singleton
Component(modules = arrayOf(DradddleModule::class))
public interface DradddleComponent {

    public fun inject(application: DradddleApplication)

    public fun inject(mainActivity: MainActivity)
    public fun inject(aboutActivity: AboutActivity)
    public fun inject(dribbbleActivity: DribbbleActivity)
    public fun inject(settingsActivity: SettingsActivity)

    public fun inject(popularShotsFragment: PopularShotsFragment)
    public fun inject(debutShotsFragment: DebutShotsFragment)
    public fun inject(recentShotsFragment: RecentShotsFragment)

    public fun inject(shotsFragmentPagerAdapter: ShotsFragmentPagerAdapter)

    public fun inject(dradddlePreferences: DradddlePreferences)

    public fun inject(versionInfo: VersionInfo)
    public fun inject(dimensionUtil: DimensionUtil)
    public fun inject(dradddlePicasso: DradddlePicasso)
}