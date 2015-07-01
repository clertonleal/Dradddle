package com.hpedrorodrigues.dradddle.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hpedrorodrigues.dradddle.R

public class ShotsHolder : RecyclerView.ViewHolder {

    public var shot: ImageView
    public var thumbnail: ImageView
    public var title: TextView
    public var authorName: TextView
    public var viewsCount: TextView
    public var likesCount: TextView
    public var commentsCount: TextView
    public var postingDate: TextView
    public var loading: LinearLayout

    constructor(view: View) : super(view) {
        this.shot = view.findViewById(R.id.shot) as ImageView
        this.thumbnail = view.findViewById(R.id.thumbnail) as ImageView
        this.title = view.findViewById(R.id.title) as TextView
        this.authorName = view.findViewById(R.id.author_name) as TextView
        this.viewsCount = view.findViewById(R.id.views_count) as TextView
        this.likesCount = view.findViewById(R.id.likes_count) as TextView
        this.commentsCount = view.findViewById(R.id.comments_count) as TextView
        this.postingDate = view.findViewById(R.id.posting_date) as TextView
        this.loading = view.findViewById(R.id.loading) as LinearLayout
    }
}