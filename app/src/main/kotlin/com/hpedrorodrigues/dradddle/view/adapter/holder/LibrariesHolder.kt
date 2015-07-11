package com.hpedrorodrigues.dradddle.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.hpedrorodrigues.dradddle.R

public class LibrariesHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val name: TextView
    val authorName: TextView

    init {
        name = view.findViewById(R.id.name) as TextView
        authorName = view.findViewById(R.id.author_name) as TextView
    }
}