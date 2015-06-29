package com.hpedrorodrigues.dradddle.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.dagger.DradddleComponent

public abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun injectMembers()

    protected enum class SupportedAnimations {FADE, ZOOM, SLIDE_LEFT, SLIDE_RIGHT, SLIDE_UP, SLIDE_DOWN}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    override fun finish() {
        super.finish()
        overrideTransition(SupportedAnimations.FADE)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overrideTransition(SupportedAnimations.FADE)
    }

    override fun invalidateOptionsMenu() {
        super.invalidateOptionsMenu()
        closeKeyBoard()
    }

    protected fun dribbbleComponent(): DradddleComponent {
        return DradddleApplication.dradddleComponent
    }

    protected fun openKeyBoard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    protected fun closeKeyBoard() {
        val view = getCurrentFocus();
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
        }
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

    private fun <A : BaseActivity> start(activityClass: Class<A>, animation: SupportedAnimations) {
        startActivity(Intent(this, activityClass))
        overrideTransition(animation)
    }

    private fun <A : BaseActivity> startWithResult(
            activityClass: Class<A>, requestCode: Int, animation: SupportedAnimations) {
        startActivityForResult(Intent(this, activityClass), requestCode)
        overrideTransition(animation)
    }

    private fun replaceFragment(containerId: Int, fragment: Fragment, animation: SupportedAnimations) {
        val transaction = getSupportFragmentManager().beginTransaction()

        when (animation) {
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
    }
}