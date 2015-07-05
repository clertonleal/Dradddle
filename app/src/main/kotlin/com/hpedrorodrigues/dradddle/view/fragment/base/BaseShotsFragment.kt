package com.hpedrorodrigues.dradddle.view.fragment.base

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.entity.Page
import com.hpedrorodrigues.dradddle.network.DradddleNetwork
import com.hpedrorodrigues.dradddle.observable.NetworkStateObservable
import com.hpedrorodrigues.dradddle.service.ConnectionService
import com.hpedrorodrigues.dradddle.view.adapter.ShotsAdapter
import com.malinskiy.superrecyclerview.SuperRecyclerView
import com.malinskiy.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener
import com.yoavst.kotlin.e
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.platform.platformStatic

public abstract class BaseShotsFragment : BaseFragment() {

    companion object {

        platformStatic val DISPLAY_ITEMS_COUNT = 15
        platformStatic val MAX_RETRIES = 3L
        platformStatic val INIT_PAGE = 0
    }

    private var superRecyclerView: SuperRecyclerView? = null
    private var swipeLayout: SwipeRefreshLayout? = null
    private var withoutNetworkLayout: LinearLayout? = null
    private var networkStateObserver: Observer = Observer { observable, data -> reloadSmallViews() }
    private var actualPage: Int = INIT_PAGE

    var shotsAdapter: ShotsAdapter? = null
        @Inject set

    var connectionService: ConnectionService? = null
        @Inject set

    var context: Context? = null
        @Inject set

    protected abstract fun retrievePage(pageNumber: Int): Observable<Page>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shots, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        withoutNetworkLayout = view.findViewById(R.id.without_network) as LinearLayout
        superRecyclerView = view.findViewById(R.id.super_recycler_view) as SuperRecyclerView
        swipeLayout = superRecyclerView!!.getSwipeToRefresh()

        configSuperRecyclerView()
        loadNextPage(actualPage)

        NetworkStateObservable.addObserver(networkStateObserver)
    }

    override fun onDestroy() {
        NetworkStateObservable.deleteObserver(networkStateObserver)

        super.onDestroy()
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
            actualPage = INIT_PAGE
            reloadSmallViews()
            loadNextPage(actualPage)
        })

        superRecyclerView!!.setupMoreListener({
            numberOfItems: Int, numberBeforeMore: Int, currentItemPos: Int ->

            reloadSmallViews()
            loadNextPage(actualPage)
        }, DISPLAY_ITEMS_COUNT)

        superRecyclerView!!.setupSwipeToDismiss(
                object: SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

                    override fun canDismiss(position: Int): Boolean {
                        return true
                    }

                    override fun onDismiss(recyclerView: RecyclerView, reversePositions: IntArray) {
                        reversePositions.forEach { shotsAdapter!!.removeShot(it) }
                    }
                })
    }

    private fun loadNextPage(pageNumber: Int) {
        if (connectionService!!.hasConnection()) {
            showLayoutLoading()
        } else {
            showLargeLayoutWithoutNetwork()
        }

        val subscription = retrievePage(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(MAX_RETRIES)
                .subscribe({
                    shotsAdapter!!.addPage(it)
                }, {
                    e(it.getMessage()!!)
                }, {
                    swipeLayout!!.setRefreshing(false)
                    hideLayoutMoreProgress()
                    actualPage++
                })
        compositeSubscription.add(subscription)
    }

    private fun showSmallLayoutWithoutNetwork() {
        withoutNetworkLayout!!.setVisibility(View.VISIBLE)
        withoutNetworkLayout!!.startAnimation(AnimationUtils.loadAnimation(context, R.anim.jump))
    }

    private fun hideSmallLayoutWithoutNetwork() {
        withoutNetworkLayout!!.setVisibility(View.GONE)
    }

    private fun showLargeLayoutWithoutNetwork() {
        val emptyView = superRecyclerView!!.getEmptyView()
        emptyView.findViewById(R.id.without_network).setVisibility(View.VISIBLE)
        emptyView.findViewById(R.id.loading).setVisibility(View.GONE)
    }

    private fun showLayoutLoading() {
        val emptyView = superRecyclerView!!.getEmptyView()
        emptyView.findViewById(R.id.without_network).setVisibility(View.GONE)
        emptyView.findViewById(R.id.loading).setVisibility(View.VISIBLE)
    }

    private fun hideLayoutMoreProgress() {
        superRecyclerView!!.getMoreProgressView().setVisibility(View.GONE)
    }

    private fun reloadSmallViews() {
        if (connectionService!!.hasConnection()) {
            hideSmallLayoutWithoutNetwork()
        } else {
            showSmallLayoutWithoutNetwork()
        }
    }
}