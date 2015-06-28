package com.hpedrorodrigues.dradddle.view.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseNavigationFragment

public class SettingsFragment : BaseNavigationFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}