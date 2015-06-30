package com.hpedrorodrigues.dradddle.view.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

public class SquareImageView : ImageView {

    public constructor(context: Context) : super(context) {
    }

    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = getMeasuredWidth()
        setMeasuredDimension(width, width)
    }
}