package com.hpedrorodrigues.dradddle.view.activity

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.view.fragment.BaseFragment
import com.hpedrorodrigues.dradddle.view.fragment.HomeFragment
import com.hpedrorodrigues.dradddle.view.fragment.ProfileFragment
import com.hpedrorodrigues.dradddle.view.fragment.SettingsFragment
import com.hpedrorodrigues.dradddle.view.widget.DradddleSearchView
import kotlinx.android.synthetic.activity_main.toolbar
import kotlinx.android.synthetic.activity_main.drawer_layout
import kotlinx.android.synthetic.activity_main.navigation_view
import java.util.HashMap
import kotlin.platform.platformStatic

public class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {

        platformStatic val DRAWER_REPLACE_SCREEN_DELAY = 400L
        platformStatic val fragments = object : HashMap<Int, BaseFragment>() {
            init {
                put(R.id.drawer_profile, ProfileFragment())
                put(R.id.drawer_home, HomeFragment())
                put(R.id.drawer_settings, SettingsFragment())
            }
        }
    }

    protected var searchView: DradddleSearchView? = null
    protected var drawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar)
        configNavigationView()
        navigateTo(R.id.drawer_home)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.main, menu)
        searchView = DradddleSearchView(this)

        val searchItem : MenuItem = menu.findItem(R.id.action_search)
        MenuItemCompat.setActionView(searchItem, searchView)

        return super<BaseActivity>.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.setChecked(true)
        drawer_layout.closeDrawer(GravityCompat.START)
        navigateTo(item.getItemId())
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super<BaseActivity>.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.support.v7.appcompat.R.id.home ->
                return drawerToggle!!.onOptionsItemSelected(item)
            else ->
                return super<BaseActivity>.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super<BaseActivity>.onBackPressed()
        }
    }

    override fun injectMembers() {
        dribbbleComponent().inject(this)
    }

    protected fun configNavigationView() {
        navigation_view.setNavigationItemSelectedListener(this)
        navigation_view.getMenu().findItem(R.id.drawer_home).setChecked(true)

        drawerToggle = object: ActionBarDrawerToggle(this, drawer_layout, toolbar as Toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            override fun onDrawerOpened(drawerView: View?) {
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View?) {
                invalidateOptionsMenu()
            }
        }

        drawer_layout.setDrawerListener(drawerToggle)
        drawerToggle!!.syncState()

        configNavigationHeaderView()
    }

    protected fun configNavigationHeaderView() {
        val headerView: View = navigation_view.inflateHeaderView(R.layout.drawer_header)
        headerView.setOnClickListener(object: View.OnClickListener {

            override fun onClick(view: View?) {
                navigation_view.getMenu().findItem(R.id.drawer_home).setChecked(true)
                drawer_layout.closeDrawer(GravityCompat.START)
                navigateTo(R.id.drawer_profile)
            }
        })
    }

    private fun navigateTo(itemId: Int) {
        Handler().postDelayed(object : Runnable {

            override fun run() {
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.animator.start, R.animator.end)
                        .replace(R.id.container, fragments.get(itemId))
                        .commit()
            }
        }, DRAWER_REPLACE_SCREEN_DELAY)
    }
}