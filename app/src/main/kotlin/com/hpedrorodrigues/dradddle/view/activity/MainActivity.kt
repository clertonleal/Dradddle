package com.hpedrorodrigues.dradddle.view.activity

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler

import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar

import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemIds
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemIds.HOME
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemIds.PROFILE
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemIds.ABOUT
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemIds.SETTINGS
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseNavigationFragment
import com.hpedrorodrigues.dradddle.view.fragment.navigation.AboutFragment
import com.hpedrorodrigues.dradddle.view.fragment.navigation.HomeFragment
import com.hpedrorodrigues.dradddle.view.fragment.navigation.ProfileFragment
import com.hpedrorodrigues.dradddle.view.fragment.navigation.SettingsFragment

import kotlinx.android.synthetic.activity_main.toolbar
import kotlinx.android.synthetic.activity_main.drawer_layout
import kotlinx.android.synthetic.activity_main.navigation_view

import java.util.HashMap

import kotlin.platform.platformStatic

public class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {

        platformStatic val DRAWER_REPLACE_SCREEN_DELAY = 400L
        platformStatic val fragments = object: HashMap<DrawerItemIds, String>() {
            init {
                put(HOME, javaClass<HomeFragment>().getName())
                put(PROFILE, javaClass<ProfileFragment>().getName())
                put(ABOUT, javaClass<AboutFragment>().getName())
                put(SETTINGS, javaClass<SettingsFragment>().getName())
            }
        }
    }

    protected var drawerToggle: ActionBarDrawerToggle? = null
    protected var currentFragmentId: DrawerItemIds? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar)
        configNavigationView()
        navigateTo(HOME)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.setChecked(true)
        drawer_layout.closeDrawer(GravityCompat.START)
        navigateTo(DrawerItemIds.find(item.getItemId()))
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
        if (isDrawerOpened()) {
            closeDrawer()
        } else if (currentFragmentId != HOME) {
            val itemHome = navigation_view.getMenu().findItem(R.id.drawer_home)
            onNavigationItemSelected(itemHome)
        } else {
            super<BaseActivity>.onBackPressed()
        }
    }

    override fun injectMembers() {
        dribbbleComponent().inject(this)
    }

    protected fun configNavigationView() {
        navigation_view.setNavigationItemSelectedListener(this)
        onNavigationItemSelected(navigation_view.getMenu().findItem(R.id.drawer_home))

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
    }

    protected fun isDrawerOpened(): Boolean {
        return drawer_layout.isDrawerOpen(GravityCompat.START)
    }

    protected fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun navigateTo(itemId: DrawerItemIds) {
        Handler().postDelayed(object: Runnable {

            override fun run() {
                val fragment = Fragment.instantiate(getBaseContext(), fragments.get(itemId))

                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.container, fragment)
                        .commit()

                currentFragmentId = itemId
            }
        }, DRAWER_REPLACE_SCREEN_DELAY)
    }
}