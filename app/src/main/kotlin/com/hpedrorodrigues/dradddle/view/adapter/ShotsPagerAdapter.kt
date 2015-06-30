package com.hpedrorodrigues.dradddle.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.enumeration.Shots
import com.hpedrorodrigues.dradddle.view.fragment.shot.DebutsShotsFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.PopularShotsFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.RecentShotsFragment
import javax.inject.Inject
import kotlin.platform.platformStatic

public class ShotsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var context: Context? = null
        @Inject set

    init {
        DradddleApplication.component().inject(this)
    }

    override fun getItem(position: Int): Fragment {
        return when(Shots.find(position)) {
            Shots.POPULAR -> PopularShotsFragment()
            Shots.RECENT -> RecentShotsFragment()
            Shots.DEBUTS -> DebutsShotsFragment()
            else -> throw IllegalArgumentException("Invalid position $position at getItem")
        }
    }

    override fun getCount(): Int {
        return Shots.size()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(Shots.find(position)) {
            Shots.POPULAR -> context!!.getString(R.string.popular)
            Shots.RECENT -> context!!.getString(R.string.recent)
            Shots.DEBUTS -> context!!.getString(R.string.debuts)
            else -> throw IllegalArgumentException("Invalid position $position at getPageTitle")
        }
    }
}