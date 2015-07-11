package com.hpedrorodrigues.dradddle.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.hpedrorodrigues.dradddle.R

public class AboutHeaderHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView

    init {
        title = view.findViewById(R.id.title) as TextView
    }
}