package com.hpedrorodrigues.dradddle.enumeration

import com.hpedrorodrigues.dradddle.R
import kotlin.platform.platformStatic

enum class DrawerItem(val itemId: Int) {
    HOME(R.id.drawer_home),
    DRIBBBLE(R.id.drawer_dribbble),
    SETTINGS(R.id.drawer_settings);

    companion object {

        platformStatic fun find(itemId: Int): DrawerItem {
            return DrawerItem.values().filter { it.itemId == itemId } [0]
        }
    }
}