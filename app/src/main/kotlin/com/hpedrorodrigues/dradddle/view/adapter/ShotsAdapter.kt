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

    var inflater: LayoutInflater? = null
        @Inject set

    var context: Context? = null
        @Inject set

    private var shots: MutableList<Shot> = ArrayList<Shot>()
    private var onShotClickListener: OnShotClickListener? = null

    @Inject
    constructor() : super() {}

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShotsHolder {
        return ShotsHolder(inflater!!.inflate(R.layout.shots, parent, false))
    }

    override fun onBindViewHolder(holder: ShotsHolder, position: Int) {
        val shot: Shot = shots.get(position)

        holder.title.setText(shot.title)
        holder.authorName.setText(shot.player!!.name)
        holder.viewsCount.setText(shot.viewsCount.toString())
        holder.likesCount.setText(shot.likesCount.toString())
        holder.commentsCount.setText(shot.commentsCount.toString())

        DradddlePicasso.loadAvatar(shot.player!!.avatarUrl!!).into(holder.authorAvatar)
        DradddlePicasso.loadShot(shot.imageUrl!!).into(holder.shot)

        holder.view.setOnClickListener {
            if (onShotClickListener != null) {
                onShotClickListener!!.onShotClick(shot.id!!)
            }
        }
    }

    override fun getItemCount(): Int = shots.size()

    public fun addPage(page: Page) {
        shots.addAll(page.shots!!)
        notifyDataSetChanged()
    }

    public fun cleanShots() {
        shots.clear()
        notifyDataSetChanged()
    }

    public fun getIds(): LongArray = shots.map { it.id as Long }.toLongArray()

    public fun setOnShotClickListener(listener: OnShotClickListener) {
        onShotClickListener = listener
    }

    public interface OnShotClickListener {

        fun onShotClick(id: Long)
    }
}