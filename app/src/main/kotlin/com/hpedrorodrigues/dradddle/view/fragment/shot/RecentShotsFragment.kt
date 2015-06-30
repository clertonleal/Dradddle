package com.hpedrorodrigues.dradddle.view.fragment.shot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseShotsFragment

public class RecentShotsFragment : BaseShotsFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recent_shots, container, false)
    }
    
    override fun injectMembers() {
        dradddleComponent().inject(this)
    }
}