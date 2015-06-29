package com.hpedrorodrigues.dradddle.view.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler

import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar

import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemId
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemId.HOME
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemId.PROFILE
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemId.ABOUT
import com.hpedrorodrigues.dradddle.enumeration.DrawerItemId.SETTINGS
import com.hpedrorodrigues.dradddle.view.adapter.HomePagerAdapter

import kotlinx.android.synthetic.activity_main.toolbar
import kotlinx.android.synthetic.activity_main.drawerLayout
import kotlinx.android.synthetic.activity_main.navigationView
import kotlinx.android.synthetic.activity_main.pager
import kotlinx.android.synthetic.activity_main.tabs
import kotlinx.android.synthetic.activity_main.fabDone

import java.util.HashMap

import kotlin.platform.platformStatic

public class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {

        platformStatic val DRAWER_REPLACE_SCREEN_DELAY = 400L
        platformStatic val REQUEST_PROFILE = 1
        platformStatic val REQUEST_ABOUT = 2
        platformStatic val REQUEST_SETTINGS = 3
    }

    protected var drawerToggle: ActionBarDrawerToggle? = null
    protected var currentItemId: DrawerItemId? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar)
        configNavigationView()
        configViewPager()
        configFabDone()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_PROFILE, REQUEST_ABOUT, REQUEST_SETTINGS ->
                getMenuItem(R.id.drawer_home).setChecked(true)
        }
        super<BaseActivity>.onActivityResult(requestCode, resultCode, data)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.setChecked(true)
        drawerLayout.closeDrawer(GravityCompat.START)
        navigateTo(DrawerItemId.find(item.getItemId()))
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
        } else {
            super<BaseActivity>.onBackPressed()
        }
    }

    override fun injectMembers() {
        dribbbleComponent().inject(this)
    }

    protected fun configNavigationView() {
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.inflateHeaderView(R.layout.drawer_header)
                .setOnClickListener { onNavigationItemSelected(getMenuItem(R.id.drawer_profile)) }

        drawerToggle = object: ActionBarDrawerToggle(this, drawerLayout, toolbar as Toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            override fun onDrawerOpened(drawerView: View?) {
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View?) {
                invalidateOptionsMenu()
            }
        }

        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle!!.syncState()
    }

    protected fun configViewPager() {
        pager.setAdapter(HomePagerAdapter(getSupportFragmentManager()))
        tabs.setupWithViewPager(pager)
    }

    protected fun configFabDone() {
        fabDone.setOnClickListener { view: View ->
            Snackbar.make(view, "Share this app", Snackbar.LENGTH_LONG).setAction("OK", {}).show()
        }
    }

    protected fun isDrawerOpened(): Boolean {
        return drawerLayout.isDrawerOpen(GravityCompat.START)
    }

    protected fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun getMenuItem(itemId: Int): MenuItem {
        return navigationView.getMenu().findItem(itemId)
    }

    private fun navigateTo(itemId: DrawerItemId) {
        Handler().postDelayed(object: Runnable {

            override fun run() {
                when (itemId) {
                    HOME -> {}
                    PROFILE -> startWithResultAndSlideDown(javaClass<ProfileActivity>(), REQUEST_PROFILE)
                    ABOUT -> startWithResultAndSlideLeft(javaClass<AboutActivity>(), REQUEST_ABOUT)
                    SETTINGS -> startWithResultAndSlideRight(javaClass<SettingsActivity>(), REQUEST_SETTINGS)
                    else -> throw IllegalArgumentException("Invalid item id $itemId")
                }
                currentItemId = itemId
            }
        }, DRAWER_REPLACE_SCREEN_DELAY)
    }
}