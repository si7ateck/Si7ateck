package com.si7ateck.dz.ui.doctor.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.databinding.FragmentDoctorItemBinding
import com.si7ateck.dz.ui.doctor.DoctorItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var dataList = emptyList<Doctor>()
    lateinit var viewModel:DoctorItemViewModel
    class MyViewHolder(private val binding: FragmentDoctorItemBinding) : RecyclerView.ViewHolder(binding.root){

         fun bind(doctor: Doctor, viewModel: DoctorItemViewModel){

            binding.doctor = doctor
            binding.viewmodeld = viewModel
            
            
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