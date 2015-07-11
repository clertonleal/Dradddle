package com.hpedrorodrigues.dradddle.util

import android.content.Context
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import javax.inject.Inject

public object DradddlePicasso {

    var context: Context? = null
        @Inject set

    init {
        DradddleApplication.component().inject(this)
    }

    public fun loadAvatar(url: String): RequestCreator {
        return Picasso.with(context).load(url).placeholder(R.mipmap.ic_account).error(R.mipmap.ic_account)
    }

    public fun loadShot(url: String): RequestCreator {
        return Picasso.with(context).load(url).placeholder(R.mipmap.loading).error(R.mipmap.without_network)
    }
}