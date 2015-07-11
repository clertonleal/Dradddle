package com.hpedrorodrigues.dradddle.view.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler

import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar

import android.view.MenuItem
import android.view.View
import android.widget.Toast

import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.constant.RequestCode
import com.hpedrorodrigues.dradddle.enumeration.DrawerItem
import com.hpedrorodrigues.dradddle.enumeration.DrawerItem.*
import com.hpedrorodrigues.dradddle.util.DradddlePreferences
import com.hpedrorodrigues.dradddle.view.adapter.ShotsFragmentPagerAdapter

import kotlinx.android.synthetic.activity_main.drawerLayout
import kotlinx.android.synthetic.activity_main.navigationView
import kotlinx.android.synthetic.activity_main.toolbar
import kotlinx.android.synthetic.activity_main.pager
import kotlinx.android.synthetic.activity_main.tabs

import java.util.HashMap

import kotlin.platform.platformStatic

public class MainActivity : BaseActivity() {

    companion object {

        platformStatic val DRAWER_REPLACE_SCREEN_DELAY = 400L
        platformStatic val CLOSE_APP_DELAY = 2000L
        platformStatic val classes = object: HashMap<DrawerItem, Class<out BaseActivity>>() {
            init {
                put(DrawerItem.DRIBBBLE, javaClass<DribbbleActivity>())
                put(DrawerItem.SETTINGS, javaClass<SettingsActivity>())
            }
        }
    }

    protected var drawerToggle: ActionBarDrawerToggle? = null
    protected var currentItem: DrawerItem? = null
    protected var backPressedOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setTitle(R.string.home)
        configNavigationView()
        configViewPager()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RequestCode.REQUEST_DRIBBBLE, RequestCode.REQUEST_SETTINGS ->
                getMenuItem(R.id.drawer_home).setChecked(true)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
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
        } else if (DradddlePreferences.getBoolean(DradddleConstants.CLOSE_APP)) {
            if (backPressedOnce) {
                super.onBackPressed()
            } else {
                backPressedOnce = true
                Toast.makeText(this, R.string.back_again_to_exit, Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ backPressedOnce = false }, CLOSE_APP_DELAY)
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
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
                    val item = getMenuItem(R.id.drawer_dribbble)
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
        pager!!.setAdapter(ShotsFragmentPagerAdapter(getSupportFragmentManager()))
        tabs!!.setupWithViewPager(pager)
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
                DRIBBBLE -> startWithResultAndFade(classes.get(DRIBBBLE), RequestCode.REQUEST_DRIBBBLE)
                SETTINGS -> startWithResultAndFade(classes.get(SETTINGS), RequestCode.REQUEST_SETTINGS)
                else -> throw IllegalArgumentException("Invalid item $item")
            }
            currentItem = item
        }, DRAWER_REPLACE_SCREEN_DELAY)
    }
}