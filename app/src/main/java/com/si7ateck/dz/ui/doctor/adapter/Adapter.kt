package com.si7ateck.dz.ui.doctor.adapter


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
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

    class MyViewHolder(private val binding: FragmentDoctorItemBinding, private val expandListener : ExpandListener) : RecyclerView.ViewHolder(binding.root){

         fun bind(doctor: Doctor, viewModel: DoctorItemViewModel, position: Int, size :Int){

            binding.doctor = doctor
            binding.viewmodeld = viewModel


             binding.baseCardview.setOnClickListener { view ->
                 if (binding.expandableLayout.visibility == View.GONE || binding.expandableLayout.visibility == View.INVISIBLE){
                     expand()

                     expandListener?.let {
                             expandListener.onExpand(position , size )
                     }

                 }else{
                     collapse()

                    /* expandListener?.let {
                     //    if ( position == 0 || position == size - 1  )
                             expandListener.onExpand(position)
                     }*/
                 }

             }
            binding.executePendingBindings()

         }

        private fun expand(){
            binding.expandableLayout.visibility = View.VISIBLE
            val widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            binding.expandableLayout.measure(widthSpec, heightSpec)

            val mAnimator = slideAnimator(0, binding.expandableLayout.getMeasuredHeight())
            mAnimator.start()
        }


        private fun collapse() {
            val finalHeight = binding.expandableLayout.getHeight()

            val mAnimator = slideAnimator(finalHeight, 0)

            mAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.expandableLayout.setVisibility(View.GONE)
                }
            })

            mAnimator.start()

       }

        private fun slideAnimator(start: Int, end: Int): ValueAnimator {

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
            fun from(parent: ViewGroup, expandListener:ExpandListener): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentDoctorItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding, expandListener
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent, expandListener
        )
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem,viewModel, position, dataList.size)
    }

    fun setData(doctorlist: List<Doctor>,viewModel: DoctorItemViewModel){
        val doctorDiffUtil = DiffUtill(dataList, doctorlist)
        val doctorDiffResult = DiffUtil.calculateDiff(doctorDiffUtil)
        this.dataList = doctorlist
        this.viewModel=viewModel
        doctorDiffResult.dispatchUpdatesTo(this)
    }

}