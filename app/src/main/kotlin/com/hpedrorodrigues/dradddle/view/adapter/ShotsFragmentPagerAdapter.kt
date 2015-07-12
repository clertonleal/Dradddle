package com.hpedrorodrigues.dradddle.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.DebutShotsFragment
import com.hpedrorodrigues.dradddle.view.fragment.shot.PopularShotsFragment
import java.util.ArrayList
import javax.inject.Inject

public class ShotsFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {

        val POPULAR_SHOTS_POSITION = 0
        val DEBUT_SHOTS_POSITION = 1
    }

    private val fragments = object: ArrayList<BaseFragment>() {
        init {
            add(PopularShotsFragment())
            add(DebutShotsFragment())
        }
    }

    init {
        DradddleApplication.component().inject(this)
    }

    var context: Context? = null
        @Inject set

    override fun getItem(position: Int): Fragment {
        return when(position) {
            POPULAR_SHOTS_POSITION, DEBUT_SHOTS_POSITION -> fragments.get(position)
            else -> throw IllegalArgumentException("Invalid position $position at getItem")
        }
    }

    override fun getCount(): Int {
        return fragments.size()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            POPULAR_SHOTS_POSITION -> context!!.getString(R.string.popular)
            DEBUT_SHOTS_POSITION -> context!!.getString(R.string.debuts)
            else -> throw IllegalArgumentException("Invalid position $position at getPageTitle")
        }
    }
}