package com.hpedrorodrigues.dradddle.view.activity

import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.transition.Slide
import android.view.View
import com.hpedrorodrigues.dradddle.R
import kotlinx.android.synthetic.activity_about.toolbar
import kotlinx.android.synthetic.activity_about.fabShare

public class AboutActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar as Toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)

        fabShare.setOnClickListener { view: View ->
            Snackbar.make(view, "Share this app", Snackbar.LENGTH_LONG)
                    .setAction(android.R.string.ok, {}).show()
        }
    }

    override fun injectMembers() {
        dribbbleComponent().inject(this)
    }
}