package com.si7ateck.dz.utility.filter

import android.animation.TimeInterpolator
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.si7ateck.dz.R
import com.si7ateck.dz.utility.helper.utils.bindView
import com.si7ateck.dz.utility.helper.utils.setScale

class FiltersTabsAdapter(context: Context, private val listener: (Int) -> Unit) : RecyclerView.Adapter<FiltersTabsAdapter.FiltersTabsViewHolder>() {

    private val payloadUpdateBadge = "UPDATE_BADGE"
    private val toggleAnimDuration = context.resources.getInteger(R.integer.toggleAnimDuration).toLong()

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private var hasFilters = MutableList(FiltersLayout.numTabs) { false }

    ///////////////////////////////////////////////////////////////////////////
    // Methods
    ///////////////////////////////////////////////////////////////////////////

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getItemCount(): Int = FiltersLayout.numTabs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersTabsViewHolder =
            FiltersTabsViewHolder(inflater.inflate(R.layout.item_filter_tab, parent, false))

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: FiltersTabsViewHolder, position: Int) {
        holder.itemView.setScale(defaultScale)
        holder.badge.setScale(if (hasFilters[position]) 1f else 0f)

        holder.itemView.setOnClickListener { listener(position) }
    }

    /**
     * This is called when badge state is updated so animate the badge
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: FiltersTabsViewHolder, position: Int, payloads: MutableList<Any>) {
        payloads.firstOrNull { it is String && it == payloadUpdateBadge }
                ?: return super.onBindViewHolder(holder, position, payloads)

        val badgeShown = hasFilters[position]

        @Suppress("USELESS_CAST")
        val timeInterpolator =
                if (badgeShown) OvershootInterpolator(3f) as TimeInterpolator
                else AccelerateInterpolator()

        holder.badge.animate()
                .scaleX(if (badgeShown) 1f else 0f)
                .scaleY(if (badgeShown) 1f else 0f)
                .setDuration(toggleAnimDuration)
                .setInterpolator(timeInterpolator)
                .start()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateBadge(position: Int, hasFilters: Boolean) {
        if (this.hasFilters[position] == hasFilters) return
        this.hasFilters[position] = hasFilters
        notifyItemChanged(position, payloadUpdateBadge)
    }

    class FiltersTabsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val badge: View by bindView(R.id.tab_badge)
    }

    companion object {
        const val defaultScale = 0.9f
        const val maxScale = 1.15f
    }
}
