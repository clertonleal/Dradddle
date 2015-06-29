package com.hpedrorodrigues.dradddle.view.fragment.shot

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.view.adapter.PopularShotsAdapter
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseShotsFragment
import com.malinskiy.superrecyclerview.SuperRecyclerView
import javax.inject.Inject

public class PopularShotsFragment : BaseShotsFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val superRecyclerView = inflater
                .inflate(R.layout.fragment_popular_shots, container, false) as SuperRecyclerView

        superRecyclerView.setLayoutManager(LinearLayoutManager(superRecyclerView.getContext()))
        superRecyclerView.setAdapter(PopularShotsAdapter())

        return superRecyclerView
    }
}