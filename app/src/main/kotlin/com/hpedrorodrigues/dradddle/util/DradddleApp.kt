package com.hpedrorodrigues.dradddle.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import javax.inject.Inject

public object DradddleApp {

    public fun view(activity: Activity) {
        val packageName = activity.getPackageName()

        try {
            val uri = Uri.parse(DradddleConstants.PLAY_STORE_APP_URL + packageName)
            activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
        } catch (e: ActivityNotFoundException) {
            val uri = Uri.parse(DradddleConstants.PLAY_STORE_WEB_URL + packageName)
            activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    public fun share(activity: Activity) {
        val message = activity.getString(R.string.share_app_message,
                DradddleConstants.PLAY_STORE_WEB_URL + activity.getPackageName())

        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.setType(DradddleConstants.TEXT_PLAIN_TYPE)
        activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.choose_app)))
    }

    public fun openBrowser(activity: Activity, url: String) {
        try {
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            Snackbar.make(activity.getCurrentFocus(), "Browser not found", Snackbar.LENGTH_SHORT).show()
        }
    }
}