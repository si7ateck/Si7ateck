package com.si7ateck.dz.ui.pharmacy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.si7ateck.dz.data.pharmacy.Pharmacy
import com.si7ateck.dz.databinding.FragmentPharmacyBinding

class AdapterPharmacy : RecyclerView.Adapter<AdapterPharmacy.MyViewHolder>() {
    var dataList = emptyList<Pharmacy>()

    class MyViewHolder(private val binding: FragmentPharmacyBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(pharmacy: Pharmacy){
            binding.pharmacy = pharmacy
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentPharmacyBinding.inflate(layoutInflater, parent, false)
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
        holder.bind(currentItem)
    }

    fun setDatap(pharmacylist: List<Pharmacy>){
        val pharmacyDiffUtil = DiffUtill(dataList, pharmacylist)
        val pharmacyDiffResult = DiffUtil.calculateDiff(pharmacyDiffUtil)
        this.dataList = pharmacylist
        pharmacyDiffResult.dispatchUpdatesTo(this)
    }

}