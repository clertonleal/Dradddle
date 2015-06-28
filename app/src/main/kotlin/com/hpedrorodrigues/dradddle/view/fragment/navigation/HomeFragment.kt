package com.hpedrorodrigues.dradddle.view.fragment.navigation

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.Toolbar
import android.view.*

import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.view.activity.MainActivity
import com.hpedrorodrigues.dradddle.view.adapter.HomePagerAdapter
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseNavigationFragment
import com.hpedrorodrigues.dradddle.view.widget.DradddleSearchView

import kotlinx.android.synthetic.fragment_home.pager
import kotlinx.android.synthetic.fragment_home.tab

public class HomeFragment : BaseNavigationFragment() {

    protected var searchView: DradddleSearchView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        pager.setAdapter(HomePagerAdapter(getChildFragmentManager()))
        tab.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home, menu)
        searchView = DradddleSearchView(getActivity()/*inject context*/)

        val searchItem : MenuItem = menu.findItem(R.id.action_search)
        MenuItemCompat.setActionView(searchItem, searchView)

        super.onCreateOptionsMenu(menu, inflater)
    }
}