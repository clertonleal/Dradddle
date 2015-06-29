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
import com.hpedrorodrigues.dradddle.enumeration.DrawerItem
import com.hpedrorodrigues.dradddle.enumeration.DrawerItem.HOME
import com.hpedrorodrigues.dradddle.enumeration.DrawerItem.PROFILE
import com.hpedrorodrigues.dradddle.enumeration.DrawerItem.ABOUT
import com.hpedrorodrigues.dradddle.enumeration.DrawerItem.SETTINGS
import com.hpedrorodrigues.dradddle.view.adapter.HomePagerAdapter
import com.hpedrorodrigues.dradddle.view.widget.DradddleSearchView

import kotlinx.android.synthetic.activity_main.toolbar
import kotlinx.android.synthetic.activity_main.drawerLayout
import kotlinx.android.synthetic.activity_main.navigationView
import kotlinx.android.synthetic.activity_main.pager
import kotlinx.android.synthetic.activity_main.tabs
import kotlinx.android.synthetic.activity_main.fabDone

import java.util.HashMap

import kotlin.platform.platformStatic

public class MainActivity : BaseActivity() {

    companion object {

        platformStatic val DRAWER_REPLACE_SCREEN_DELAY = 400L

        platformStatic val REQUEST_PROFILE = 1
        platformStatic val REQUEST_ABOUT = 2
        platformStatic val REQUEST_SETTINGS = 3

        platformStatic val classes = object: HashMap<DrawerItem, Class<out BaseActivity>>() {
            init {
                put(DrawerItem.PROFILE, javaClass<ProfileActivity>())
                put(DrawerItem.ABOUT, javaClass<AboutActivity>())
                put(DrawerItem.SETTINGS, javaClass<SettingsActivity>())
            }
        }
    }

    protected var drawerToggle: ActionBarDrawerToggle? = null
    protected var currentItem: DrawerItem? = null
    protected var searchView: DradddleSearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.main, menu)

        searchView = DradddleSearchView(this)
        val searchItem : MenuItem = menu.findItem(R.id.action_search)
        MenuItemCompat.setActionView(searchItem, searchView)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.support.v7.appcompat.R.id.home ->
                return drawerToggle!!.onOptionsItemSelected(item)
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (isDrawerOpened()) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    override fun injectMembers() {
        dribbbleComponent().inject(this)
    }

    protected fun configNavigationView() {
        navigationView.setNavigationItemSelectedListener(
                object: NavigationView.OnNavigationItemSelectedListener {

                    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                        item.setChecked(true)
                        closeDrawer()
                        navigateTo(DrawerItem.find(item.getItemId()))
                        return true
                    }
                })

        navigationView.inflateHeaderView(R.layout.drawer_header)
                .setOnClickListener {
                    val item = getMenuItem(R.id.drawer_profile)
                    item.setChecked(true)
                    closeDrawer()
                    navigateTo(DrawerItem.find(item.getItemId()))
                }

        drawerToggle = object: ActionBarDrawerToggle(this, drawerLayout, toolbar as Toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            override fun onDrawerOpened(drawerView: View?) {
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View?) {
                invalidateOptionsMenu()
            }
        }

        getMenuItem(R.id.drawer_home).setChecked(true)

        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle!!.syncState()
    }

    protected fun configViewPager() {
        pager.setAdapter(HomePagerAdapter(getSupportFragmentManager()))
        tabs.setupWithViewPager(pager)
    }

    protected fun configFabDone() {
        fabDone.setOnClickListener { view ->
            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                    .setAction(android.R.string.ok, {}).show()
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

    private fun navigateTo(item: DrawerItem) {
        Handler().postDelayed({
            when (item) {
                HOME -> {}
                PROFILE -> startWithResultAndSlideDown(classes.get(PROFILE), REQUEST_PROFILE)
                ABOUT -> startWithResultAndSlideLeft(classes.get(ABOUT), REQUEST_ABOUT)
                SETTINGS -> startWithResultAndZoom(classes.get(SETTINGS), REQUEST_SETTINGS)
                else -> throw IllegalArgumentException("Invalid item id $item")
            }
            currentItem = item
        }, DRAWER_REPLACE_SCREEN_DELAY)
    }
}