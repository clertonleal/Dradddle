package com.hpedrorodrigues.dradddle.view.fragment.shot

import com.hpedrorodrigues.dradddle.view.fragment.base.BaseShotsFragment

public class PopularShotsFragment : BaseShotsFragment() {

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }
}