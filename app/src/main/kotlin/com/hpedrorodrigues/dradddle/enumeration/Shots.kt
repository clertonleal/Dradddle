package com.hpedrorodrigues.dradddle.enumeration

import kotlin.platform.platformStatic

enum class Shots(private val position: Int) {
    POPULAR(0),
    RECENT(1),
    DEBUTS(2);

    companion object {

        platformStatic fun find(position: Int): Shots {
            return Shots.values().filter { _ -> _.getPosition() == position } [0]
        }

        platformStatic fun size(): Int {
            return Shots.values().size()
        }
    }

    public fun getPosition(): Int {
        return position
    }
}