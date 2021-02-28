package com.si7ateck.dz.ui.doctor.adapter

import androidx.recyclerview.widget.DiffUtil
import com.si7ateck.dz.data.doctor.Doctor

class DiffUtill (
    private val oldList: List<Doctor>,
    private val newList: List<Doctor>
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
                    && oldList[oldItemPosition]._speciality == newList[newItemPosition]._speciality
                    && oldList[oldItemPosition]._type == newList[newItemPosition]._type

        }
    }
