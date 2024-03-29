package com.hpedrorodrigues.dradddle.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.CompoundButton
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.util.DradddleApp
import com.hpedrorodrigues.dradddle.util.DradddleMail
import com.hpedrorodrigues.dradddle.util.DradddlePreferences
import kotlinx.android.synthetic.activity_settings.toolbar
import kotlinx.android.synthetic.activity_settings.close_app
import kotlinx.android.synthetic.activity_settings.toggle_close_the_app
import kotlinx.android.synthetic.activity_settings.about_the_app
import kotlinx.android.synthetic.activity_settings.rate_the_app
import kotlinx.android.synthetic.activity_settings.share_the_app
import kotlinx.android.synthetic.activity_settings.report_a_bug
import kotlinx.android.synthetic.activity_settings.idea_to_improve
import kotlinx.android.synthetic.activity_settings.send_us_your_feedback
import kotlinx.android.synthetic.activity_settings.contact_us
import javax.inject.Inject

public class SettingsActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar as Toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        loadValues()
        configListeners()
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }

    protected fun loadValues() {
        toggle_close_the_app.setChecked(DradddlePreferences.getBoolean(DradddleConstants.CLOSE_APP))
    }

    protected fun configListeners() {
        close_app.setOnClickListener {
            val isChecked = toggle_close_the_app.isChecked()
            DradddlePreferences.putBoolean(DradddleConstants.CLOSE_APP, !isChecked)
            toggle_close_the_app.setChecked(!isChecked)
        }
        toggle_close_the_app.setOnCheckedChangeListener {
            compoundButton: CompoundButton, isChecked: Boolean ->
            DradddlePreferences.putBoolean(DradddleConstants.CLOSE_APP, isChecked)
        }
        about_the_app.setOnClickListener { startWithFade(javaClass<AboutActivity>()) }
        rate_the_app.setOnClickListener { DradddleApp.view(this) }
        share_the_app.setOnClickListener { DradddleApp.share(this) }
        report_a_bug.setOnClickListener { DradddleMail.sendReportBugEmail(this) }
        idea_to_improve.setOnClickListener { DradddleMail.sendImproveAppEmail(this) }
        send_us_your_feedback.setOnClickListener { DradddleMail.sendFeedbackEmail(this) }
        contact_us.setOnClickListener { DradddleMail.sendContactUsEmail(this) }
    }
}