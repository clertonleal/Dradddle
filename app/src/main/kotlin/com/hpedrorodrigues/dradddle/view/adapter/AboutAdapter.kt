package com.hpedrorodrigues.dradddle.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.enumeration.LibraryInfo
import com.hpedrorodrigues.dradddle.view.adapter.holder.AboutHeaderHolder
import com.hpedrorodrigues.dradddle.view.adapter.holder.DeveloperHolder
import com.hpedrorodrigues.dradddle.view.adapter.holder.LibrariesHolder
import javax.inject.Inject

public class AboutAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    companion object {

        val TYPE_HEADER = 0
        val TYPE_DEVELOPER = 1
        val TYPE_ITEM = 2
    }

    var inflater: LayoutInflater? = null
        @Inject set

    var context: Context? = null
        @Inject set

    private var onLibraryClickListener: OnLibraryClickListener? = null
    private var onDeveloperClickListener: OnDeveloperClickListener? = null

    @Inject
    constructor() : super() {}

    override fun getItemCount(): Int = LibraryInfo.size()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_DEVELOPER
            1 -> TYPE_HEADER
            else -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DEVELOPER -> DeveloperHolder(inflater!!.inflate(R.layout.developer, parent, false))
            TYPE_HEADER -> AboutHeaderHolder(inflater!!.inflate(R.layout.about_header, parent, false))
            else -> LibrariesHolder(inflater!!.inflate(R.layout.library, parent, false))
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_DEVELOPER) {
            val holder = viewHolder as DeveloperHolder
            holder.name.setText(DradddleConstants.DEVELOPER_NAME)
            holder.view.setOnClickListener {
                if (onDeveloperClickListener != null) {
                    onDeveloperClickListener!!.onClick()
                }
            }
        } else if (getItemViewType(position) == TYPE_ITEM) {
            val holder = viewHolder as LibrariesHolder

            val library = LibraryInfo.find(position)

            holder.name.setText(library.name)
            holder.authorName.setText(library.authorName)

            holder.view.setOnClickListener {
                if (onLibraryClickListener != null) {
                    onLibraryClickListener!!.onClick(library)
                }
            }
        }
    }

    public fun setOnLibraryClickListener(listener: OnLibraryClickListener) {
        onLibraryClickListener = listener
    }

    public fun setOnDeveloperClickListener(listener: OnDeveloperClickListener) {
        onDeveloperClickListener = listener
    }

    public interface OnLibraryClickListener {

        fun onClick(library: LibraryInfo)
    }

    public interface OnDeveloperClickListener {

        fun onClick()
    }
}