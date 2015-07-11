package com.hpedrorodrigues.dradddle.util

import android.content.Context
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import javax.inject.Inject

public object DimensionUtil {

    var context: Context? = null
        @Inject set

    init {
        DradddleApplication.component().inject(this)
    }

    public fun loadDimensionDP(resourceId: Int): Int {
        val resources = context!!.getResources()
        return (resources.getDimension(resourceId) / resources.getDisplayMetrics().density).toInt()
    }
}