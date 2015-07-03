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

        platformStatic val DISPLAY_ITEMS_COUNT = 15
        platformStatic val FIRST_PAGE_SHOTS = 1
    }

    private var superRecyclerView: SuperRecyclerView? = null
    private var swipeLayout: SwipeRefreshLayout? = null

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

        superRecyclerView = view as SuperRecyclerView
        swipeLayout = superRecyclerView!!.getSwipeToRefresh()

        configSuperRecyclerView()

        if (connectionService!!.hasConnection()) {
            loadPage(FIRST_PAGE_SHOTS)
        }
    }

    private fun configSuperRecyclerView() {
        superRecyclerView!!.setLayoutManager(LinearLayoutManager(superRecyclerView!!.getContext()))
        superRecyclerView!!.setAdapter(shotsAdapter)

        superRecyclerView!!.setRefreshingColorResources(
                R.color.primary,
                R.color.primary_dark,
                R.color.accent_translucent,
                R.color.dark_gray)

        superRecyclerView!!.setRefreshListener({
            shotsAdapter!!.cleanShots()
            loadPage(FIRST_PAGE_SHOTS)
        })

        superRecyclerView!!.setupMoreListener({
            numberOfItems: Int, numberBeforeMore: Int, currentItemPos: Int ->
            loadPage(numberOfItems + 1 % DISPLAY_ITEMS_COUNT)
        }, DISPLAY_ITEMS_COUNT)

        superRecyclerView!!.setupSwipeToDismiss(object: SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

            override fun canDismiss(position: Int): Boolean {
                return true
            }

            override fun onDismiss(recyclerView: RecyclerView, reverseSortedPositions: kotlin.IntArray) {
                reverseSortedPositions.forEach { shotsAdapter!!.removeShot(it) }
            }
        })
    }

    private fun loadPage(pageNumber: Int) {
        val subscription = dradddleNetwork!!
                .retrievePage(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shotsAdapter!!.addPage(it) }, { e(it.getMessage()!!) }, {
                    swipeLayout!!.setRefreshing(false)
                    hideLayoutMoreProgress()
                })
        compositeSubscription.add(subscription)
    }

    private fun hideLayoutMoreProgress() {
        superRecyclerView!!.getMoreProgressView().setVisibility(View.GONE)
    }
}