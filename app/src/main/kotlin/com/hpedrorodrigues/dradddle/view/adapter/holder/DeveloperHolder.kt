package com.hpedrorodrigues.dradddle.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hpedrorodrigues.dradddle.R

public class DeveloperHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val name: TextView
    val function: TextView
    val avatar: ImageView

    init {
        name = view.findViewById(R.id.name) as TextView
        function = view.findViewById(R.id.function) as TextView
        avatar = view.findViewById(R.id.avatar) as ImageView
    }
}