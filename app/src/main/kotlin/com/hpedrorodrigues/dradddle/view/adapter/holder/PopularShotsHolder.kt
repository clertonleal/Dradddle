package com.hpedrorodrigues.dradddle.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hpedrorodrigues.dradddle.R

public class PopularShotsHolder : RecyclerView.ViewHolder {

    private var view: View
    private var shotView: ImageView
    private var descriptionView: TextView
    private var loadingView: LinearLayout

    constructor(view: View) : super(view) {
        this.view = view;
        this.shotView = view.findViewById(R.id.shot) as ImageView
        this.descriptionView = view.findViewById(R.id.description) as TextView
        this.loadingView = view.findViewById(R.id.loading) as LinearLayout
    }

    override fun toString(): String {
        return "${super.toString()} -> Text: ${descriptionView.getText()}"
    }

    public fun getView(): View {
        return view
    }

    public fun getShotView(): ImageView {
        return shotView
    }

    public fun getDescriptionVew(): TextView {
        return descriptionView
    }

    public fun getLoadingVew(): LinearLayout {
        return loadingView
    }
}