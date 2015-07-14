package com.hpedrorodrigues.dradddle.view.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.view.adapter.ShotFragmentPagerAdapter
import kotlinx.android.synthetic.activity_shot.pager

public class ShotActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shot)
        configViewPager()
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }

    private fun configViewPager() {
        val adapter = ShotFragmentPagerAdapter(getSupportFragmentManager())
        adapter.swapIds(getIntent().getLongArrayExtra(DradddleConstants.SHOT_IDS))
        pager!!.setAdapter(adapter)
    }
}