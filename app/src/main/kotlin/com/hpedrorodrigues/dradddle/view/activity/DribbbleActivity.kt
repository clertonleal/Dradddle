package com.hpedrorodrigues.dradddle.view.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Html
import android.view.MenuItem
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.util.DradddleApp
import kotlinx.android.synthetic.activity_dribbble.toolbar
import kotlinx.android.synthetic.activity_dribbble.collapsing_toolbar
import kotlinx.android.synthetic.activity_dribbble.dribbble_button
import kotlinx.android.synthetic.activity_dribbble.dribbble_twitter
import kotlinx.android.synthetic.activity_dribbble.dribbble_facebook
import kotlinx.android.synthetic.activity_dribbble.dribbble_instagram
import kotlinx.android.synthetic.activity_dribbble.dribbble_site

public class DribbbleActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dribbble)
        setSupportActionBar(toolbar as Toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        setTranslucentToolbar()
        configFloatActionButton()
        configImageButtons()

        collapsing_toolbar.setTitle(getString(R.string.dribbble))
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }

    private fun configFloatActionButton() {
        dribbble_button.setOnClickListener {
            DradddleApp.openBrowser(this, DradddleConstants.DRIBBBLE_WEB_ABOUT_URL)
        }
    }

    private fun configImageButtons() {
        dribbble_site.setOnClickListener {
            DradddleApp.openBrowser(this, DradddleConstants.DRIBBBLE_URL)
        }
        dribbble_facebook.setOnClickListener {
            DradddleApp.openFacebookPage(this, DradddleConstants.DRIBBBLE_SOCIAL_NETWORK_ID)
        }
        dribbble_instagram.setOnClickListener {
            DradddleApp.openInstagramProfile(this, DradddleConstants.DRIBBBLE_SOCIAL_NETWORK_ID)
        }
        dribbble_twitter.setOnClickListener {
            DradddleApp.openTwitterProfile(this, DradddleConstants.DRIBBBLE_SOCIAL_NETWORK_ID)
        }
    }

    private fun setTranslucentToolbar() {
        (toolbar.getBackground() as ColorDrawable)
                .setAlpha(DradddleConstants.MIN_VALUE_ALPHA.toInt())
    }
}