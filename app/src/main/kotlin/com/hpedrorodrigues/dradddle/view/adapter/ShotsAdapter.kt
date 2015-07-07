package com.hpedrorodrigues.dradddle.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.entity.Page
import com.hpedrorodrigues.dradddle.view.adapter.holder.ShotsHolder
import com.hpedrorodrigues.dradddle.entity.Shot
import com.hpedrorodrigues.dradddle.util.DradddlePicasso
import java.util.ArrayList
import javax.inject.Inject

public class ShotsAdapter : RecyclerView.Adapter<ShotsHolder> {

    @Inject
    constructor() : super() {}

    var inflater: LayoutInflater? = null
        @Inject set

    var context: Context? = null
        @Inject set

    private var shots: List<Shot> = ArrayList<Shot>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShotsHolder {
        return ShotsHolder(inflater!!.inflate(R.layout.shots_item, parent, false))
    }

    override fun onBindViewHolder(holder: ShotsHolder, position: Int) {
        val shot: Shot = shots.get(position)

        holder.title.setText(shot.title)
        holder.authorName.setText(shot.player!!.name)
        holder.viewsCount.setText(shot.viewsCount.toString())
        holder.likesCount.setText(shot.likesCount.toString())
        holder.commentsCount.setText(shot.commentsCount.toString())
        DradddlePicasso.with(context!!, shot.imageUrl!!).into(holder.shot)
        DradddlePicasso.with(context!!, shot.player!!.avatarUrl!!).into(holder.authorAvatar)
    }

    override fun getItemCount(): Int {
        return shots.size()
    }

    public fun addPage(page: Page) {
        shots = shots.plus(page.shots!!)
        notifyDataSetChanged()
    }

    public fun removeShot(position: Int) {
        shots.drop(position)
        notifyDataSetChanged()
    }

    public fun cleanShots() {
        shots.dropWhile { true }
        notifyDataSetChanged()
    }
}