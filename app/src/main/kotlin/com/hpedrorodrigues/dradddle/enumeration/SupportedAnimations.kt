package com.hpedrorodrigues.dradddle.enumeration

import kotlin.platform.platformStatic

public enum class SupportedAnimations(val order: Int) {
    FADE(0),
    ZOOM(1),
    SLIDE_LEFT(2),
    SLIDE_RIGHT(3),
    SLIDE_UP(4),
    SLIDE_DOWN(5);

    companion object {

        platformStatic public fun find(order: Int): SupportedAnimations {
            return SupportedAnimations.values().filter { it.order == order } [0]
        }
    }
}