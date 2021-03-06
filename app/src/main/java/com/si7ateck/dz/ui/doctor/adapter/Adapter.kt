package com.si7ateck.dz.ui.doctor.adapter


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.databinding.FragmentDoctorItemBinding
import com.si7ateck.dz.ui.doctor.DoctorItemViewModel
import com.si7ateck.dz.ui.doctor.ExpandListener


class Adapter(var expandListener : ExpandListener): RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var dataList = emptyList<Doctor>()
    lateinit var viewModel:DoctorItemViewModel

    private lateinit var recyclerView: RecyclerView

    var oldPosition : Int = -1

    class MyViewHolder(val binding: FragmentDoctorItemBinding) : RecyclerView.ViewHolder(binding.root){

         fun expand(){
            binding.expandableLayout.visibility = View.VISIBLE
            val widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            binding.expandableLayout.measure(widthSpec, heightSpec)

            val mAnimator = slideAnimator(0, binding.expandableLayout.getMeasuredHeight())
            mAnimator.start()
        }


         fun collapse() {
            val finalHeight = binding.expandableLayout.getHeight()

            val mAnimator = slideAnimator(finalHeight, 0)

            mAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.expandableLayout.setVisibility(View.GONE)
                }
            })

            mAnimator.start()

        }

         fun slideAnimator(start: Int, end: Int): ValueAnimator {

            var animator = ValueAnimator.ofInt(start, end)

            animator.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams: ViewGroup.LayoutParams = binding.expandableLayout.getLayoutParams()
                layoutParams.height = value
                binding.expandableLayout.setLayoutParams(layoutParams)
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



        holder.binding.baseCardview.setOnClickListener { view ->
            if (holder.binding.expandableLayout.visibility == View.GONE || holder.binding.expandableLayout.visibility == View.INVISIBLE){



                Log.d("erererr", "inside it")
                holder.expand()



                expandListener?.let {
                    expandListener.onExpand(position , dataList.size )
                }


                // collapse previously expanded view
                var oldViewHolder =
                    recyclerView.findViewHolderForAdapterPosition(oldPosition) as? MyViewHolder


                Log.d("erererr", "oldview is $oldViewHolder")


                oldViewHolder?.let {
                    it.collapse()
                }

                oldPosition = position
                Log.d("erererr", "old position is $oldPosition")

            }else{
                holder.collapse()
                oldPosition = -1
            }

        }
        holder.binding.executePendingBindings()
    }



    fun setData(doctorlist: List<Doctor>,viewModel: DoctorItemViewModel){
        val doctorDiffUtil = DiffUtill(dataList, doctorlist)
        val doctorDiffResult = DiffUtil.calculateDiff(doctorDiffUtil)
        this.dataList = doctorlist
        this.viewModel=viewModel
        doctorDiffResult.dispatchUpdatesTo(this)
    }

}