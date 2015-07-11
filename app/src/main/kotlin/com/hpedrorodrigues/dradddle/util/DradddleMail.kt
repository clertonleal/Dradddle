package com.hpedrorodrigues.dradddle.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import javax.inject.Inject

public object DradddleMail {

    public fun sendImproveAppEmail(activity: Activity) {
        send(activity, R.string.suggestions)
    }

    public fun sendReportBugEmail(activity: Activity) {
        send(activity, R.string.new_bug)
    }

    public fun sendFeedbackEmail(activity: Activity) {
        send(activity, R.string.feedback)
    }

    public fun sendContactUsEmail(activity: Activity) {
        send(activity, R.string.contact)
    }

    private fun send(activity: Activity, resSubjectId: Int) {
        try {
            val intent = buildEmailIntent(activity, resSubjectId)
            intent.setClassName(DradddleConstants.GMAIL_CLASS_NAME_1, DradddleConstants.GMAIL_CLASS_NAME_2)
            activity.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val intent = buildEmailIntent(activity, resSubjectId)
            activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.choose_app)))
        }
    }

    private fun buildEmailIntent(activity: Activity, resSubjectId: Int): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(DradddleConstants.DEVELOPER_EMAIL))
        val subject = "${DradddleConstants.DEFAULT_SUBJECT} ${activity.getString(resSubjectId)}"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, DeviceInfo.DETAILS)
        intent.setType(DradddleConstants.DEFAULT_EMAIL_TYPE)
        return intent
    }
}