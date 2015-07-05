package com.hpedrorodrigues.dradddle.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.enumeration.Shots
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.DebutShotsFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.PopularShotsFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.RecentShotsFragment
import java.util.ArrayList
import javax.inject.Inject
import kotlin.platform.platformStatic

public class ShotsFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {

        platformStatic val fragments = object: ArrayList<BaseFragment>() {
            init {
                add(PopularShotsFragment())
                add(RecentShotsFragment())
                add(DebutShotsFragment())
            }
        }
    }

    init {
        DradddleApplication.component().inject(this)
    }

    var context: Context? = null
        @Inject set

    override fun getItem(position: Int): Fragment {
        return when(Shots.find(position)) {
            Shots.POPULAR, Shots.RECENT, Shots.DEBUTS -> fragments.get(position)
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