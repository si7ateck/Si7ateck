package com.si7ateck.dz.utility.customview

import android.content.Context
import android.util.AttributeSet
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import com.si7ateck.dz.R

class FilterSeekbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : CrystalRangeSeekbar(context, attrs, defStyleAttr) {

    override fun getThumbWidth(): Float = resources.getDimension(R.dimen.thumb_size)
    override fun getThumbHeight(): Float = resources.getDimension(R.dimen.thumb_size)
    override fun getBarHeight(): Float = thumbHeight / 4.5f
}
