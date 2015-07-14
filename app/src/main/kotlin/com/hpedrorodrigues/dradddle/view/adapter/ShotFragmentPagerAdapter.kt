package com.hpedrorodrigues.dradddle.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.view.fragment.shot.ShotFragment

public class ShotFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)  {

    private var ids: LongArray? = null

    override fun getItem(position: Int): Fragment = ShotFragment(ids!!.get(position))

    override fun getCount(): Int = ids!!.size()

    public fun swapIds(ids: LongArray) {
        this.ids = ids
        notifyDataSetChanged()
    }
}