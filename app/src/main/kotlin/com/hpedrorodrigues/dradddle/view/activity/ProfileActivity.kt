package com.hpedrorodrigues.dradddle.view.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.hpedrorodrigues.dradddle.R
import kotlinx.android.synthetic.activity_profile.toolbar
import kotlinx.android.synthetic.activity_profile.collapsing_toolbar

public class ProfileActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar as Toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)

        collapsing_toolbar.setTitle(getString(R.string.profile))
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }
}