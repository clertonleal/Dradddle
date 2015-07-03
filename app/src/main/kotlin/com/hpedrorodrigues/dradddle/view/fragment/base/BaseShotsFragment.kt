package com.hpedrorodrigues.dradddle.view.fragment.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.network.DradddleNetwork
import com.hpedrorodrigues.dradddle.service.ConnectionService
import com.hpedrorodrigues.dradddle.view.adapter.ShotsAdapter
import com.malinskiy.superrecyclerview.SuperRecyclerView
import com.malinskiy.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener
import com.yoavst.kotlin.e
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import kotlin.platform.platformStatic

public abstract class BaseShotsFragment : BaseFragment() {

    companion object {

        platformStatic val DISPLAY_ITEMS_COUNT = 10
        platformStatic val FIRST_PAGE_SHOTS = 1
    }

    private var superRecyclerView: SuperRecyclerView? = null
    private var swipeLayout: SwipeRefreshLayout? = null
    private var emptyView: LinearLayout? = null

    var dradddleNetwork: DradddleNetwork? = null
        @Inject set

    var shotsAdapter: ShotsAdapter? = null
        @Inject set

    var connectionService: ConnectionService? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shots, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        superRecyclerView = view.findViewById(R.id.superRecyclerView) as SuperRecyclerView
        emptyView = view.findViewById(R.id.emptyView) as LinearLayout
        swipeLayout = superRecyclerView!!.getSwipeToRefresh()

        configSwipeLayout()
        configSuperRecyclerView()

        if (connectionService!!.hasConnection()) {
            loadPage(FIRST_PAGE_SHOTS)
        } else {
            showEmptyView()
        }
    }

    private fun configSwipeLayout() {
        swipeLayout!!.setColorSchemeResources(
                R.color.primary,
                R.color.primary_dark,
                R.color.accent_translucent,
                R.color.dark_gray)
    }

    private fun configSuperRecyclerView() {
        superRecyclerView!!.getRecyclerView().setHasFixedSize(true)
        superRecyclerView!!.setLayoutManager(LinearLayoutManager(superRecyclerView!!.getContext()))
        superRecyclerView!!.setAdapter(shotsAdapter)

        superRecyclerView!!.setRefreshListener({
            shotsAdapter!!.cleanShots()
            loadPage(FIRST_PAGE_SHOTS)
        })

        superRecyclerView!!.setupMoreListener({
            numberOfItems: Int, numberBeforeMore: Int, currentItemPos: Int ->
            loadPage(numberOfItems + 1 % DISPLAY_ITEMS_COUNT)
        }, DISPLAY_ITEMS_COUNT)
    }

    private fun showEmptyView() {
        emptyView!!.setVisibility(View.VISIBLE)
        superRecyclerView!!.setVisibility(View.GONE)
    }

    private fun showShotsView() {
        emptyView!!.setVisibility(View.GONE)
        superRecyclerView!!.setVisibility(View.VISIBLE)
    }

    private fun loadPage(pageNumber: Int) {
        val subscription = dradddleNetwork!!.retrievePage(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext({showEmptyView()})
                .subscribe({ page ->
                    showShotsView()
                    shotsAdapter!!.addPage(page)
                    if (swipeLayout!!.isEnabled()) {
                        swipeLayout!!.setRefreshing(false)
                    }
                }, {
                    e(it.getMessage()!!)
                })
        compositeSubscription.add(subscription)
    }
}