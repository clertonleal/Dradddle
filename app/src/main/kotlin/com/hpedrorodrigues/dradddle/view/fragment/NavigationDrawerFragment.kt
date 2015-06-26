package com.hpedrorodrigues.dradddle.view.fragment

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.listener.NavigationDrawerCallbacks
import kotlin.properties.Delegates

public class NavigationDrawerFragment : BaseFragment() {

    private var callbacks: NavigationDrawerCallbacks? = null
    private var drawerLayout: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var containerView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            callbacks = activity as NavigationDrawerCallbacks?
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity must implement NavigationDrawerCallbacks.")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    public fun config(container: View, drawer: DrawerLayout, toolbar: Toolbar) {
        containerView = container
        drawerLayout = drawer

        drawerLayout!!.setStatusBarBackgroundColor(getResources().getColor(R.color.color_primary))

        drawerToggle = object: ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            override fun onDrawerClosed(drawerView: View?) {
                getActivity().invalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View?) {
                getActivity().invalidateOptionsMenu()
            }
        }
        drawerLayout!!.post(object : Runnable {
            override fun run() = drawerToggle!!.syncState()
        })
        drawerLayout!!.setDrawerListener(drawerToggle)
    }

    public fun openDrawer() {
        drawerLayout!!.openDrawer(containerView)
    }

    public fun closeDrawer() {
        drawerLayout!!.closeDrawer(containerView)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }
}