package com.hpedrorodrigues.dradddle.enumeration

import com.hpedrorodrigues.dradddle.R
import kotlin.platform.platformStatic

enum class DrawerItem(private val itemId: Int) {
    HOME(R.id.drawer_home),
    PROFILE(R.id.drawer_profile),
    ABOUT(R.id.drawer_about),
    SETTINGS(R.id.drawer_settings);

    companion object {

        platformStatic fun find(itemId: Int): DrawerItem {
            return DrawerItem.values().filter { _ -> _.getItemId() == itemId } [0]
        }
    }

    public fun getItemId(): Int {
        return itemId
    }
}