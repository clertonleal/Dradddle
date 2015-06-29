package com.hpedrorodrigues.dradddle.view.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.hpedrorodrigues.dradddle.R
import kotlinx.android.synthetic.activity_settings.toolbar

public class SettingsActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar as Toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
    }

    override fun injectMembers() {
        dribbbleComponent().inject(this)
    }
}