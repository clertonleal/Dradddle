package com.hpedrorodrigues.dradddle.enumeration

import kotlin.platform.platformStatic

public enum class AnimationsInfo(
        private val animation: SupportedAnimations, private val reverseAnimation: SupportedAnimations) {

    FADE(SupportedAnimations.FADE, SupportedAnimations.FADE),
    ZOOM(SupportedAnimations.ZOOM, SupportedAnimations.ZOOM),
    SLIDE_LEFT(SupportedAnimations.SLIDE_LEFT, SupportedAnimations.SLIDE_RIGHT),
    SLIDE_RIGHT(SupportedAnimations.SLIDE_RIGHT, SupportedAnimations.SLIDE_LEFT),
    SLIDE_UP(SupportedAnimations.SLIDE_UP, SupportedAnimations.SLIDE_DOWN),
    SLIDE_DOWN(SupportedAnimations.SLIDE_DOWN, SupportedAnimations.SLIDE_UP);

    companion object {

        platformStatic fun findReverseByAnimation(animation: SupportedAnimations): SupportedAnimations {
            return AnimationsInfo.values()
                    .filter { _ -> _.getAnimation() == animation } [0]
                    .getReverseAnimation()
        }
    }

    public fun getAnimation(): SupportedAnimations {
        return animation
    }

    public fun getReverseAnimation(): SupportedAnimations {
        return reverseAnimation
    }
}