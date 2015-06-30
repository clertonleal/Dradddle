package com.hpedrorodrigues.dradddle.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hpedrorodrigues.dradddle.R

public class PopularShotsHolder : RecyclerView.ViewHolder {

    private var view: View
    private var imageView: ImageView
    private var textView: TextView

    constructor(view: View) : super(view) {
        this.view = view;
        this.imageView = view.findViewById(R.id.avatar) as ImageView
        this.textView = view.findViewById(R.id.text) as TextView
    }

    override fun toString(): String {
        return "${super.toString()} -> Text: ${textView.getText()}"
    }

    public fun getView(): View {
        return view
    }

    public fun getImageView(): ImageView {
        return imageView
    }

    public fun getTextVew(): TextView {
        return textView
    }
}