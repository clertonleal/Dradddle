package com.hpedrorodrigues.dradddle.view.activity

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.listener.NavigationDrawerCallbacks
import com.hpedrorodrigues.dradddle.view.fragment.NavigationDrawerFragment
import com.hpedrorodrigues.dradddle.view.widget.DradddleSearchView
import kotlinx.android.synthetic.activity_main.toolbar
import kotlinx.android.synthetic.activity_main.drawer_layout
import kotlinx.android.synthetic.activity_main.navigation_drawer

public class MainActivity : BaseActivity(), NavigationDrawerCallbacks {

    protected var searchView: DradddleSearchView? = null
    protected var navigationDrawerFragment: NavigationDrawerFragment? = null

    override fun injectMembers() {
        dribbbleComponent().inject(this)
    }

    override fun onNavigationDrawerItemSelected(position: Int) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigationDrawerFragment = getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer) as NavigationDrawerFragment

        val fragmentView: View = findViewById(R.id.navigation_drawer)
        navigationDrawerFragment!!.config(fragmentView, drawer_layout, toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)

        searchView = DradddleSearchView(this)

        val searchItem : MenuItem? = menu!!.findItem(R.id.action_search)
        MenuItemCompat.setActionView(searchItem, searchView)

        return super<BaseActivity>.onCreateOptionsMenu(menu)
    }
}