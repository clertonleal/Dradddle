package com.hpedrorodrigues.dradddle.enumeration

public enum class AnimationsInfo(
        val animation: SupportedAnimations, val reverseAnimation: SupportedAnimations) {

    FADE(SupportedAnimations.FADE, SupportedAnimations.FADE),
    ZOOM(SupportedAnimations.ZOOM, SupportedAnimations.ZOOM),
    SLIDE_LEFT(SupportedAnimations.SLIDE_LEFT, SupportedAnimations.SLIDE_RIGHT),
    SLIDE_RIGHT(SupportedAnimations.SLIDE_RIGHT, SupportedAnimations.SLIDE_LEFT),
    SLIDE_UP(SupportedAnimations.SLIDE_UP, SupportedAnimations.SLIDE_DOWN),
    SLIDE_DOWN(SupportedAnimations.SLIDE_DOWN, SupportedAnimations.SLIDE_UP);

    companion object {

        public fun findReverseByAnimation(animation: SupportedAnimations): SupportedAnimations {
            return AnimationsInfo.values().filter { it.animation == animation } [0].reverseAnimation
        }
    }
}