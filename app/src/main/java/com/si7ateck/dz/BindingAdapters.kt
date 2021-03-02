package com.si7ateck.dz

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.si7ateck.dz.repository.DoctorRepository
import com.si7ateck.dz.ui.doctor.DoctorItemFragment
import com.si7ateck.dz.ui.doctor.DoctorItemViewModel
import org.jetbrains.annotations.NotNull

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(
            view: ShapeableImageView,
            url: String
        ) { // This methods should not have any return type, = declaration would make it return that object declaration.
            Glide.with(view.context).load(url).into(view)
        }



        @JvmStatic
        @NotNull
        @BindingAdapter( "getadrees2")
        fun getAddress2(view: MaterialTextView, address: String) {

            view.text=address
            Log.d("akram",address)
        }
    }
}
