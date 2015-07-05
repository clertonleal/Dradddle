package com.hpedrorodrigues.dradddle.view.fragment.shot

import com.hpedrorodrigues.dradddle.entity.Page
import com.hpedrorodrigues.dradddle.network.DradddleNetwork
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseShotsFragment
import rx.Observable
import javax.inject.Inject

public class RecentShotsFragment : BaseShotsFragment() {

    var dradddleNetwork: DradddleNetwork? = null
        @Inject set

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }

    override fun retrievePage(pageNumber: Int): Observable<Page> {
        return dradddleNetwork!!.retrieveRecentPage(pageNumber)
    }
}