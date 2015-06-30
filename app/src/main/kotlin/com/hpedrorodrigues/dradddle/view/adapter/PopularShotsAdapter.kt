package com.hpedrorodrigues.dradddle.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.view.adapter.holder.PopularShotsHolder
import javax.inject.Inject

public class PopularShotsAdapter() : RecyclerView.Adapter<PopularShotsHolder>() {

    var inflater: LayoutInflater? = null
        @Inject set

    init {
        DradddleApplication.component().inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PopularShotsHolder? {
        return PopularShotsHolder(inflater!!.inflate(R.layout.popular_shots_item, parent, false))
    }

    override fun onBindViewHolder(holder: PopularShotsHolder, position: Int) {
        holder.getTextVew().setText(R.string.app_name)
    }

    override fun getItemCount(): Int {
        return 200
    }
}