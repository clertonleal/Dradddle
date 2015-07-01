package com.hpedrorodrigues.dradddle.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.application.DradddleApplication
import com.hpedrorodrigues.dradddle.view.adapter.holder.ShotsHolder
import com.hpedrorodrigues.dradddle.entity.Shot
import java.util.*
import javax.inject.Inject

public class ShotsAdapter() : RecyclerView.Adapter<ShotsHolder>() {

    var inflater: LayoutInflater? = null
        @Inject set

    private val shots: List<Shot> = ArrayList<Shot>()

    init {
        DradddleApplication.component().inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShotsHolder {
        return ShotsHolder(inflater!!.inflate(R.layout.shots_item, parent, false))
    }

    override fun onBindViewHolder(holder: ShotsHolder, position: Int) {
        holder.authorName.setText("Pedro")
        holder.viewsCount.setText("122")
        holder.likesCount.setText("122")
        holder.commentsCount.setText("122")
        holder.postingDate.setText("31 jun 20153")
    }

    override fun getItemCount(): Int {
        return 40
    }
}