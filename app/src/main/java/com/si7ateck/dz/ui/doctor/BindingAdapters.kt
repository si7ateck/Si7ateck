package com.si7ateck.dz.ui.doctor

import android.util.Log
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class BindingAdapters {


    companion object{

        @BindingAdapter("android:getImageFromUri")
        @JvmStatic
        fun getImageFromUri(view: ShapeableImageView, imageUri: String){
                Glide.with(view.context)
                    .load(imageUri)
                    .into(view)

            Log.d("ImageUriAkram","imageUri is ${imageUri}" )

        }





        @BindingAdapter("android:setTextBinding")
        @JvmStatic
        fun setTextBinding(view: MaterialTextView, text : String){
            view.text = text

            Log.d("ImageUriAkram","setTextBinding called ${text}" )

        }
    }
}