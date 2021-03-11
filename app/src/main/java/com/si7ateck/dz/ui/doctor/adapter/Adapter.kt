package com.si7ateck.dz.ui.doctor.adapter


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.doOnLayout
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.si7ateck.dz.R
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.databinding.FragmentDoctorItemBinding
import com.si7ateck.dz.ui.doctor.DoctorItemViewModel
import com.si7ateck.dz.ui.doctor.ExpandListener
import com.si7ateck.dz.utility.helper.utils.*
import java.util.*


class Adapter(context: Context, var expandListener : ExpandListener): RecyclerView.Adapter<Adapter.MyViewHolder>() {


    var dataList = emptyList<Doctor>()
    lateinit var viewModel:DoctorItemViewModel
    private lateinit var recyclerView: RecyclerView

    private var oldPosition : LinkedList<Int> = LinkedList()
    private var positionExpand = -1

    var animationPlaybackSpeed: Double = 0.8


    private var expandedModel: Doctor? = null



    private val originalBg: Int by bindColor(context, R.color.list_item_bg_collapsed)
    private val expandedBg: Int by bindColor(context, R.color.list_item_bg_expanded)

    private val originalWidth = context.screenWidth - 48.dp
    private val expandedWidth = context.screenWidth - 24.dp
    private var originalHeight = -1 // will be calculated dynamically
    private var expandedHeight = -1 // will be calculated dynamically


    /** Variable used to filter adapter items. 'true' if filtered and 'false' if not */
    var isFiltered = false
        set(value) {
            field = value
        }

    private val listItemExpandDuration: Long get() = (300L / animationPlaybackSpeed).toLong()
    private var isScaledDown = false



    private fun expandItem(holder: MyViewHolder, expand: Boolean, animate: Boolean) {
        if (animate) {
            val animator = getValueAnimator(
                expand, listItemExpandDuration, AccelerateDecelerateInterpolator()
            ) { progress -> setExpandProgress(holder, progress) }

            if (expand) animator.doOnStart { holder.expandView.isVisible = true }
            else animator.doOnEnd { holder.expandView.isVisible = false }

            animator.start()
        } else {

            // show expandView only if we have expandedHeight (onViewAttached)
            holder.expandView.isVisible = expand && expandedHeight >= 0
            setExpandProgress(holder, if (expand) 1f else 0f)
        }
    }





    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)

        // get originalHeight & expandedHeight if not gotten before
        if (expandedHeight < 0) {
            expandedHeight = 0 // so that this block is only called once

            holder.cardContainer.doOnLayout { view ->
                originalHeight = view.height

                // show expandView and record expandedHeight in next layout pass
                // (doOnPreDraw) and hide it immediately. We use onPreDraw because
                // it's called after layout is done. doOnNextLayout is called during
                // layout phase which causes issues with hiding expandView.
                holder.expandView.isVisible = true
                view.doOnPreDraw {
                    expandedHeight = view.height
                    holder.expandView.isVisible = false
                }
            }
        }
    }



    private fun setExpandProgress(holder: MyViewHolder, progress: Float) {
        if (expandedHeight > 0 && originalHeight > 0) {
            holder.cardContainer.layoutParams.height =
                (originalHeight + (expandedHeight - originalHeight) * progress).toInt()
        }
        holder.cardContainer.layoutParams.width =
            (originalWidth + (expandedWidth - originalWidth) * progress).toInt()

        holder.cardContainer.setBackgroundColor(blendColors(originalBg, expandedBg, progress))
        holder.cardContainer.requestLayout()

    }


    ///////////////////////////////////////////////////////////////////////////
    // Scale Down Animation
    ///////////////////////////////////////////////////////////////////////////

    private inline val LinearLayoutManager.visibleItemsRange: IntRange
        get() = findFirstVisibleItemPosition()..findLastVisibleItemPosition()

    fun getScaleDownAnimator(isScaledDown: Boolean): ValueAnimator {
        val lm = recyclerView.layoutManager as LinearLayoutManager

        val animator = getValueAnimator(isScaledDown,
            duration = 300L, interpolator = AccelerateDecelerateInterpolator()
        ) { progress ->

            // Get viewHolder for all visible items and animate attributes
            for (i in lm.visibleItemsRange) {
                val holder = recyclerView.findViewHolderForLayoutPosition(i) as MyViewHolder
             //   setScaleDownProgress(holder, i, progress)
            }
        }

        // Set adapter variable when animation starts so that newly binded views in
        // onBindViewHolder will respect the new size when they come into the screen
        animator.doOnStart { this.isScaledDown = isScaledDown }

        // For all the non visible items in the layout manager, notify them to adjust the
        // view to the new size
        animator.doOnEnd {
            repeat(lm.itemCount) { if (it !in lm.visibleItemsRange) notifyItemChanged(it) }
        }
        return animator
    }

/*

    private fun setScaleDownProgress(holder: MyViewHolder, position: Int, progress: Float) {
        val itemExpanded = position >= 0 && dataList[position] == expandedModel
        holder.cardContainer.layoutParams.apply {
            width = ((if (itemExpanded) expandedWidth else originalWidth) * (1 - 0.1f * progress)).toInt()
            height = ((if (itemExpanded) expandedHeight else originalHeight) * (1 - 0.1f * progress)).toInt()
//            log("width=$width, height=$height [${"%.2f".format(progress)}]")
        }
        holder.cardContainer.requestLayout()

        holder.scaleContainer.scaleX = 1 - 0.01f * progress
        holder.scaleContainer.scaleY = 1 - 0.01f * progress

        holder.scaleContainer.setPadding(
            (listItemHorizontalPadding * (1 - 0.2f * progress)).toInt(),
            (listItemVerticalPadding * (1 - 0.2f * progress)).toInt(),
            (listItemHorizontalPadding * (1 - 0.2f * progress)).toInt(),
            (listItemVerticalPadding * (1 - 0.2f * progress)).toInt()
        )
    }
*/

/*

    */
/** Convenience method for calling from onBindViewHolder *//*

    private fun scaleDownItem(holder: MyViewHolder, position: Int, isScaleDown: Boolean) {
        setScaleDownProgress(holder, position, if (isScaleDown) 1f else 0f)
    }
*/

    class MyViewHolder(val binding: FragmentDoctorItemBinding) : RecyclerView.ViewHolder(binding.root){

        val expandView: View by bindView(R.id.expand_view)
        val cardContainer: View by bindView(R.id.card_container)
        val scaleContainer: View by bindView(R.id.scale_container)

        fun expand(){
            expandView.visibility = View.VISIBLE
            val widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            binding.expandView.measure(widthSpec, heightSpec)

            val mAnimator = slideAnimator(0, binding.expandView.getMeasuredHeight())
            mAnimator.start()
        }


         fun collapse() {
            val finalHeight = binding.expandView.getHeight()

            val mAnimator = slideAnimator(finalHeight, 0)

            mAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.expandView.setVisibility(View.GONE)
                }
            })

            mAnimator.start()

        }

         fun slideAnimator(start: Int, end: Int): ValueAnimator {

            var animator = ValueAnimator.ofInt(start, end)

            animator.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams: ViewGroup.LayoutParams = binding.expandView.getLayoutParams()
                layoutParams.height = value
                binding.expandView.setLayoutParams(layoutParams)
            }
            return animator

        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentDoctorItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView

    }




    override fun getItemCount(): Int {
        return  dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val doctor = dataList[position]
        holder.binding.doctor = doctor
        holder.binding.viewmodeld = viewModel

        if (position != positionExpand){
            holder?.let{
                it.collapse()
            }
        }else {
            holder?.let{
                it.expand()
            }
        }

        holder.binding.scaleContainer.setOnClickListener { view ->



            var tableIndexes = LinkedList<Int>()

            if (holder.binding.expandView.visibility == View.GONE || holder.binding.expandView.visibility == View.INVISIBLE){


                for ((index, value) in oldPosition.withIndex()) {
                    // collapse previously expanded view
                    var oldViewHolder =
                        recyclerView.findViewHolderForAdapterPosition(value) as? MyViewHolder

                    oldViewHolder?.let {oldViewHolder ->
                        oldViewHolder.collapse()
                        tableIndexes.add(value)
                    }

                }

                oldPosition.removeAll(tableIndexes)

                holder.expand()
                positionExpand = position

                expandListener?.let {
                    expandListener.onExpand(position , dataList.size )
                }

                oldPosition.add(position)

            }else{
                holder.collapse()
                positionExpand = -1
                oldPosition.remove(position)
            }

        }
        holder.binding.executePendingBindings()
    }


/*


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = dataList[position]
        holder.binding.doctor = model
        holder.binding.viewmodeld = viewModel

        expandItem(holder, model == expandedModel, animate = false)
        scaleDownItem(holder, position, isScaledDown)

        holder.cardContainer.setOnClickListener {
            if (expandedModel == null) {

                // expand clicked view
                expandItem(holder, expand = true, animate = true)
                expandedModel = model
            } else if (expandedModel == model) {

                // collapse clicked view
                expandItem(holder, expand = false, animate = true)
                expandedModel = null
            } else {

                // collapse previously expanded view
                val expandedModelPosition = dataList.indexOf(expandedModel!!)
                val oldViewHolder =
                    recyclerView.findViewHolderForAdapterPosition(expandedModelPosition) as? MyViewHolder
                if (oldViewHolder != null) expandItem(oldViewHolder, expand = false, animate = true)

                // expand clicked view
                expandItem(holder, expand = true, animate = true)
                expandedModel = model
            }
        }
    }

*/

    fun setData(doctorlist: List<Doctor>,viewModel: DoctorItemViewModel){
        val doctorDiffUtil = DiffUtill(dataList, doctorlist)
        val doctorDiffResult = DiffUtil.calculateDiff(doctorDiffUtil)
        this.dataList = doctorlist
        this.viewModel=viewModel
        doctorDiffResult.dispatchUpdatesTo(this)
    }

}