package com.hpedrorodrigues.dradddle.view.widget

import android.content.Context
import android.support.v7.widget.SearchView
import com.hpedrorodrigues.dradddle.R
import javax.inject.Inject

public class DradddleSearchView(context : Context) : SearchView(context) {

    override fun onActionViewCollapsed() {
        setQuery(null, false)
        super.onActionViewCollapsed()
    }

    override fun setQueryHint(hint: CharSequence?) {
        super.setQueryHint(hint ?: getContext().getString(R.string.search))
    }

    override fun setIconifiedByDefault(iconified: Boolean) {
        super.setIconifiedByDefault(true)
    }

    public fun close() {
        if (isActive()) onActionViewCollapsed()
    }

    public fun isActive(): Boolean {
        return !isIconified()
    }
}