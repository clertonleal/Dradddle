package com.hpedrorodrigues.dradddle.view.fragment.shot

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.view.adapter.ShotsAdapter
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseShotsFragment
import com.malinskiy.superrecyclerview.SuperRecyclerView
import com.malinskiy.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener
import com.malinskiy.superrecyclerview.swipe.SwipeLayout
import kotlin.platform.platformStatic

public class PopularShotsFragment : BaseShotsFragment() {

    companion object {

        platformStatic val DISPLAY_ITEMS_COUNT = 10
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_popular_shots, container, false)
        val superRecyclerView = view as SuperRecyclerView

        configSwipeLayout(superRecyclerView)
        configSuperRecyclerView(superRecyclerView)

        return superRecyclerView
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }

    private fun configSwipeLayout(superRecyclerView: SuperRecyclerView) {
        val swipeLayout = superRecyclerView.getSwipeToRefresh()
        swipeLayout.setColorSchemeResources(
                R.color.primary,
                R.color.primary_dark,
                R.color.accent_translucent,
                R.color.dark_gray)
    }

    private fun configSuperRecyclerView(superRecyclerView: SuperRecyclerView) {
        superRecyclerView.setLayoutManager(LinearLayoutManager(superRecyclerView.getContext()))
        superRecyclerView.setAdapter(ShotsAdapter())

        val swipeLayout = superRecyclerView.getSwipeToRefresh()

        superRecyclerView.setRefreshListener({
            swipeLayout.setRefreshing(false)
            Snackbar.make(swipeLayout, "Refresh", Snackbar.LENGTH_LONG)
                    .setAction(android.R.string.ok, {}).show()
        })

        superRecyclerView.setupMoreListener({
            numberOfItems: Int, numberBeforeMore: Int, currentItemPos: Int ->

            Snackbar.make(swipeLayout, "More", Snackbar.LENGTH_LONG)
                    .setAction(android.R.string.ok, {}).show()
        }, DISPLAY_ITEMS_COUNT)

        superRecyclerView.setupSwipeToDismiss(
                object: SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

            override public fun canDismiss(position: Int): Boolean {
                return true
            }

            override public fun onDismiss(recycler: RecyclerView, reverseSortedPositions: IntArray) {
                Snackbar.make(swipeLayout, "Dismiss", Snackbar.LENGTH_LONG)
                        .setAction(android.R.string.ok, {}).show()
            }
        })
    }
}