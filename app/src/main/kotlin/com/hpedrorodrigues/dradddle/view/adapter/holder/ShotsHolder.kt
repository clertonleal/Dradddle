package com.hpedrorodrigues.dradddle.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hpedrorodrigues.dradddle.R
import de.hdodenhof.circleimageview.CircleImageView

public class ShotsHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val shot: ImageView
    val authorAvatar: CircleImageView
    val title: TextView
    val authorName: TextView
    val viewsCount: TextView
    val likesCount: TextView
    val commentsCount: TextView
    val postingDate: TextView

    init {
        this.shot = view.findViewById(R.id.shot) as ImageView
        this.authorAvatar = view.findViewById(R.id.author_avatar) as CircleImageView
        this.title = view.findViewById(R.id.title) as TextView
        this.authorName = view.findViewById(R.id.author_name) as TextView
        this.viewsCount = view.findViewById(R.id.views_count) as TextView
        this.likesCount = view.findViewById(R.id.likes_count) as TextView
        this.commentsCount = view.findViewById(R.id.comments_count) as TextView
        this.postingDate = view.findViewById(R.id.posting_date) as TextView
    }
}