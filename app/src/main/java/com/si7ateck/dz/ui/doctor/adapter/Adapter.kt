package com.si7ateck.dz.ui.doctor.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.databinding.FragmentDoctorItemBinding
import com.si7ateck.dz.ui.doctor.DoctorItemViewModel


class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var dataList = emptyList<Doctor>()
    lateinit var viewModel:DoctorItemViewModel

    class MyViewHolder(private val binding: FragmentDoctorItemBinding) : RecyclerView.ViewHolder(binding.root){

         fun bind(doctor: Doctor, viewModel: DoctorItemViewModel){

            binding.doctor = doctor
            binding.viewmodeld = viewModel
            
            binding.baseCardview.setOnClickListener{
                if (binding.expandableLayout.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(binding.baseCardview, AutoTransition())
                    binding.expandableLayout.visibility = View.VISIBLE

                } else {
                    TransitionManager.beginDelayedTransition(binding.baseCardview, AutoTransition())
                    binding.expandableLayout.visibility = View.GONE

                }
            }
            //binding.doctorAddess.text = address

            binding.executePendingBindings()

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

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem,viewModel)
    }

    fun setData(doctorlist: List<Doctor>,viewModel: DoctorItemViewModel){
        val doctorDiffUtil = DiffUtill(dataList, doctorlist)
        val doctorDiffResult = DiffUtil.calculateDiff(doctorDiffUtil)
        this.dataList = doctorlist
        this.viewModel=viewModel
        doctorDiffResult.dispatchUpdatesTo(this)
    }

}