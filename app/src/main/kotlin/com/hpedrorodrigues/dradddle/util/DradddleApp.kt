package com.hpedrorodrigues.dradddle.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.support.design.widget.Snackbar
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import javax.inject.Inject

public object DradddleApp {

    public fun view(activity: Activity) {
        val packageName = activity.getPackageName()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(DradddleConstants.PLAY_STORE_APP_URL + packageName))
        if (!isIntentAvailable(activity, intent)) {
            intent.setData(Uri.parse(DradddleConstants.PLAY_STORE_WEB_URL + packageName))
        }
        activity.startActivity(intent)
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
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    public fun openFacebookPage(activity: Activity, pageId: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val packageManager = activity.getPackageManager()

        val appUrl = "${DradddleConstants.FACEBOOK_PAGE_APP_URL}$pageId"
        val webUrl = "${DradddleConstants.FACEBOOK_PAGE_WEB_URL}$pageId"
        val appWebModalUrl = "${DradddleConstants.FACEBOOK_PAGE_APP_WEB_MODAL_URL}$webUrl"

        try {
            val facebookVersionCode = packageManager.getPackageInfo(
                    DradddleConstants.FACEBOOK_CLASS_NAME, 0).versionCode

            if (facebookVersionCode >= DradddleConstants.FACEBOOK_CHANGE_VERSION) {
                intent.setData(Uri.parse(appWebModalUrl))
            } else {
                intent.setData(Uri.parse(appUrl))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            intent.setData(Uri.parse(webUrl))
        }
        activity.startActivity(intent)
    }

    public fun openInstagramProfile(activity: Activity, profileId: String) {
        val appUrl = "${DradddleConstants.INSTAGRAM_APP_URL}$profileId"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(appUrl))
                .setPackage(DradddleConstants.INSTAGRAM_CLASS_NAME);
        if (isIntentAvailable(activity, intent)){
            activity.startActivity(intent);
        } else{
            val webUrl = "${DradddleConstants.INSTAGRAM_WEB_URL}$profileId"
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)));
        }
    }

    public fun openTwitterProfile(activity: Activity, profileId: String) {
        val uri = Uri.parse("${DradddleConstants.TWITTER_WEB_URL}$profileId")
        activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun isIntentAvailable(activity: Activity, intent: Intent): Boolean {
        val packageManager = activity.getPackageManager()
        val list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list.size() > 0
    }
}