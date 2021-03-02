package com.si7ateck.dz.ui.pharmacy2.adapter

import androidx.recyclerview.widget.DiffUtil
import com.si7ateck.dz.data.pharmacy.Pharmacy

class DiffUtill (
    private val oldList: List<Pharmacy>,
    private val newList: List<Pharmacy>
    ): DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]._Id == newList[newItemPosition]._Id
                    && oldList[oldItemPosition]._id_firebase == newList[newItemPosition]._id_firebase
                    && oldList[oldItemPosition]._name == newList[newItemPosition]._name
                    && oldList[oldItemPosition]._phone == newList[newItemPosition]._phone


        }
    }
