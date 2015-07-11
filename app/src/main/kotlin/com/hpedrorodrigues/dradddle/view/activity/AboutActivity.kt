package com.hpedrorodrigues.dradddle.view.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.enumeration.LibraryInfo
import com.hpedrorodrigues.dradddle.util.DradddleApp
import com.hpedrorodrigues.dradddle.util.VersionInfo
import com.hpedrorodrigues.dradddle.view.adapter.AboutAdapter
import kotlinx.android.synthetic.activity_about.toolbar
import kotlinx.android.synthetic.activity_about.background_image
import kotlinx.android.synthetic.activity_about.dradddle_layout
import kotlinx.android.synthetic.activity_about.version_app
import kotlinx.android.synthetic.activity_about.recycler_view
import javax.inject.Inject

public class AboutActivity() : BaseActivity() {

    var aboutAdapter: AboutAdapter? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar as Toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        configVersionView()
        configRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> restablishToolbar()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        restablishToolbar()
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }

    private fun configVersionView() {
        version_app.setText(getString(R.string.version_app, VersionInfo.getVersionName()))
    }

    private fun configRecyclerView() {
        recycler_view.setLayoutManager(LinearLayoutManager(recycler_view.getContext()))
        recycler_view.setAdapter(aboutAdapter)

        var overAllYScroll = 0
        refreshComponentsState(overAllYScroll)
        recycler_view.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override public fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                overAllYScroll += dy
                refreshComponentsState(overAllYScroll)
            }
        })

        aboutAdapter!!.setOnDeveloperClickListener(object: AboutAdapter.OnDeveloperClickListener {

            override public fun onClick() {
                DradddleApp.openBrowser(this@AboutActivity, DradddleConstants.DEVELOPER_GITHUB)
            }
        })

        aboutAdapter!!.setOnLibraryClickListener(object: AboutAdapter.OnLibraryClickListener {

            override public fun onClick(library: LibraryInfo) {
                DradddleApp.openBrowser(this@AboutActivity, library.url)
            }
        })
    }

    private fun refreshComponentsState(scrollY: Int) {
        val reverseScrollY = -1 * scrollY

        background_image.setTranslationY((reverseScrollY / 2).toFloat())
        dradddle_layout.setTranslationY((reverseScrollY / 2.2).toFloat())

        val background = toolbar.getBackground() as ColorDrawable

        val padding = recycler_view.getPaddingTop()

        val proportion = 1 - (padding.toDouble() - scrollY.toDouble()) / padding.toDouble()
        val calculatedAlpha = proportion * DradddleConstants.MAX_VALUE_ALPHA
        var alpha: Double
        if (calculatedAlpha < DradddleConstants.MIN_VALUE_ALPHA) {
            alpha = DradddleConstants.MIN_VALUE_ALPHA
        } else if (calculatedAlpha > DradddleConstants.MAX_VALUE_ALPHA) {
            alpha = DradddleConstants.MAX_VALUE_ALPHA
        } else {
            alpha = calculatedAlpha
        }
        background.setAlpha(alpha.toInt())

        val scrollRatio = (alpha / DradddleConstants.MAX_VALUE_ALPHA).toFloat()

        val titleColor = getAlphaColor(Color.WHITE, scrollRatio)

        (toolbar as Toolbar).setTitleTextColor(titleColor)
    }

    private fun getAlphaColor(color: Int, scrollRatio: Float): Int {
        val alpha = (scrollRatio * DradddleConstants.MAX_VALUE_ALPHA).toInt()
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))
    }

    private fun restablishToolbar() {
        (toolbar as Toolbar).setTitleTextColor(Color.WHITE)
        toolbar.getBackground().setAlpha(DradddleConstants.MAX_VALUE_ALPHA.toInt())
    }
}