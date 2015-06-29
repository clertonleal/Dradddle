package com.hpedrorodrigues.dradddle.enumeration

import com.hpedrorodrigues.dradddle.R
import kotlin.platform.platformStatic

enum class DrawerItemId(private val itemId: Int) {
    HOME(R.id.drawer_home),
    PROFILE(R.id.drawer_profile),
    ABOUT(R.id.drawer_about),
    SETTINGS(R.id.drawer_settings);

    companion object {

        platformStatic fun find(itemId: Int): DrawerItemId {
            return DrawerItemId.values().filter { _ -> _.getItemId() == itemId } [0]
        }
    }

    public fun getItemId(): Int {
        return itemId
    }
}