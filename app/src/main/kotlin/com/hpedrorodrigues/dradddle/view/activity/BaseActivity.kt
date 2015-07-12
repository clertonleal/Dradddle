package com.hpedrorodrigues.dradddle.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.constant.BundleKeys
import com.hpedrorodrigues.dradddle.dagger.DradddleComponent
import com.hpedrorodrigues.dradddle.enumeration.AnimationsInfo
import com.hpedrorodrigues.dradddle.enumeration.SupportedAnimations
import com.hpedrorodrigues.dradddle.extension.*
import rx.subscriptions.CompositeSubscription

public abstract class BaseActivity : AppCompatActivity() {

    protected var currentAnimation: SupportedAnimations = SupportedAnimations.FADE
    protected var compositeSubscription: CompositeSubscription = CompositeSubscription()

    protected abstract fun injectMembers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()

        val args = getIntent()
        if (args != null && args.hasExtra(BundleKeys.ARG_ANIMATION)) {
            val animationOrder = args.getIntExtra(BundleKeys.ARG_ANIMATION, 0)
            currentAnimation = SupportedAnimations.find(animationOrder)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    override fun invalidateOptionsMenu() {
        super.invalidateOptionsMenu()
        closeKeyboard()
    }

    override fun finish() {
        super.finish()
        overrideTransitionWithReverse()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overrideTransitionWithReverse()
    }

    override fun onDestroy() {
        unsubscribeSubscriptions()
        super.onDestroy()
    }

    protected fun dradddleComponent(): DradddleComponent {
        return DradddleApplication.component()
    }

    protected fun <A : BaseActivity> startWithFade(activityClass: Class<A>) {
        start(activityClass, SupportedAnimations.FADE)
    }

    protected fun <A : BaseActivity> startWithZoom(activityClass: Class<A>) {
        start(activityClass, SupportedAnimations.ZOOM)
    }

    protected fun <A : BaseActivity> startWithSlideLeft(activityClass: Class<A>) {
        start(activityClass, SupportedAnimations.SLIDE_LEFT)
    }

    protected fun <A : BaseActivity> startWithSlideRight(activityClass: Class<A>) {
        start(activityClass, SupportedAnimations.SLIDE_RIGHT)
    }

    protected fun <A : BaseActivity> startWithSlideUp(activityClass: Class<A>) {
        start(activityClass, SupportedAnimations.SLIDE_UP)
    }

    protected fun <A : BaseActivity> startWithSlideDown(activityClass: Class<A>) {
        start(activityClass, SupportedAnimations.SLIDE_DOWN)
    }

    protected fun <A : BaseActivity> startWithResultAndFade(activityClass: Class<A>, requestCode: Int) {
        startWithResult(activityClass, requestCode, SupportedAnimations.FADE)
    }

    protected fun <A : BaseActivity> startWithResultAndZoom(activityClass: Class<A>, requestCode: Int) {
        startWithResult(activityClass, requestCode, SupportedAnimations.ZOOM)
    }

    protected fun <A : BaseActivity> startWithResultAndSlideLeft(activityClass: Class<A>, requestCode: Int) {
        startWithResult(activityClass, requestCode, SupportedAnimations.SLIDE_LEFT)
    }

    protected fun <A : BaseActivity> startWithResultAndSlideRight(activityClass: Class<A>, requestCode: Int) {
        startWithResult(activityClass, requestCode, SupportedAnimations.SLIDE_RIGHT)
    }

    protected fun <A : BaseActivity> startWithResultAndSlideUp(activityClass: Class<A>, requestCode: Int) {
        startWithResult(activityClass, requestCode, SupportedAnimations.SLIDE_UP)
    }

    protected fun <A : BaseActivity> startWithResultAndSlideDown(activityClass: Class<A>, requestCode: Int) {
        startWithResult(activityClass, requestCode, SupportedAnimations.SLIDE_DOWN)
    }

    protected fun replaceFragmentWithFade(containerId: Int, fragment: Fragment) {
        replaceFragment(containerId, fragment, SupportedAnimations.FADE)
    }

    protected fun replaceFragmentWithZoom(containerId: Int, fragment: Fragment) {
        replaceFragment(containerId, fragment, SupportedAnimations.ZOOM)
    }

    protected fun replaceFragmentWithSlideLeft(containerId: Int, fragment: Fragment) {
        replaceFragment(containerId, fragment, SupportedAnimations.SLIDE_LEFT)
    }

    protected fun replaceFragmentWithSlideRight(containerId: Int, fragment: Fragment) {
        replaceFragment(containerId, fragment, SupportedAnimations.SLIDE_RIGHT)
    }

    protected fun replaceFragmentWithSlideUp(containerId: Int, fragment: Fragment) {
        replaceFragment(containerId, fragment, SupportedAnimations.SLIDE_UP)
    }

    protected fun replaceFragmentWithSlideDown(containerId: Int, fragment: Fragment) {
        replaceFragment(containerId, fragment, SupportedAnimations.SLIDE_DOWN)
    }

    protected fun unsubscribeSubscriptions() {
        compositeSubscription.unsubscribe()
        compositeSubscription = CompositeSubscription()
    }

    private fun <A : BaseActivity> start(activityClass: Class<A>, animation: SupportedAnimations) {
        val reverseAnimation = AnimationsInfo.findReverseByAnimation(animation)
        val intent = Intent(this, activityClass)
        intent.putExtra(BundleKeys.ARG_ANIMATION, reverseAnimation.order)

        startActivity(intent)
        overrideTransition(animation)
    }

    private fun <A : BaseActivity> startWithResult(
            activityClass: Class<A>, requestCode: Int, animation: SupportedAnimations) {
        val reverseAnimation = AnimationsInfo.findReverseByAnimation(animation)
        val intent = Intent(this, activityClass)
        intent.putExtra(BundleKeys.ARG_ANIMATION, reverseAnimation.order)

        startActivityForResult(intent, requestCode)
        overrideTransition(animation)
    }

    private fun replaceFragment(containerId: Int, fragment: Fragment, animation: SupportedAnimations) {
        val transaction = getSupportFragmentManager().beginTransaction()

        val reverseAnimation = AnimationsInfo.findReverseByAnimation(animation)
        val bundle = if (fragment.getArguments() == null) Bundle() else fragment.getArguments()
        bundle.putInt(BundleKeys.ARG_ANIMATION, reverseAnimation.order)
        fragment.setArguments(bundle)

        when(animation) {
            SupportedAnimations.FADE ->
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            SupportedAnimations.ZOOM ->
                transaction.setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
            SupportedAnimations.SLIDE_LEFT ->
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            SupportedAnimations.SLIDE_RIGHT ->
                transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_out_right)
            SupportedAnimations.SLIDE_UP ->
                transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up)
            SupportedAnimations.SLIDE_DOWN ->
                transaction.setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_down)
            else -> IllegalArgumentException("Invalid animation $animation")
        }

        transaction.replace(containerId, fragment).commit()
        currentAnimation = animation
    }

    private fun overrideTransition(animation: SupportedAnimations) {
        when (animation) {
            SupportedAnimations.FADE ->
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            SupportedAnimations.ZOOM ->
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out)
            SupportedAnimations.SLIDE_LEFT ->
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            SupportedAnimations.SLIDE_RIGHT ->
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
            SupportedAnimations.SLIDE_UP ->
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            SupportedAnimations.SLIDE_DOWN ->
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
            else -> IllegalArgumentException("Invalid animation $animation")
        }
        currentAnimation = animation
    }

    private fun overrideTransitionWithReverse() {
        overrideTransition(AnimationsInfo.findReverseByAnimation(currentAnimation))
    }
}