package com.hpedrorodrigues.dradddle.view.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.dagger.DradddleComponent

public abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun injectMembers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()
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
}