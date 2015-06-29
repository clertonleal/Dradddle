package com.hpedrorodrigues.dradddle.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.view.adapter.holder.PopularShotsHolder
import javax.inject.Inject

Inject public class PopularShotsAdapter() : RecyclerView.Adapter<PopularShotsHolder>() {

    var layoutInflater: LayoutInflater? = null
        @Inject set

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PopularShotsHolder? {
        return PopularShotsHolder(LayoutInflater.from(parent!!.getContext()).inflate(R.layout.popular_shots_item, parent, false))
    }

    override fun onBindViewHolder(holder: PopularShotsHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 40
    }
}