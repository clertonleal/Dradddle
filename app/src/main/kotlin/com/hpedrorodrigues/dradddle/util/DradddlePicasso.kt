package com.hpedrorodrigues.dradddle.util

import android.content.Context
import com.hpedrorodrigues.dradddle.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlin.platform.platformStatic

public class DradddlePicasso {

    companion object {

        platformStatic public fun with(context: Context, url: String): RequestCreator {
            return Picasso.with(context).load(url).placeholder(R.mipmap.loading).error(R.mipmap.without_network)
        }
    }
}