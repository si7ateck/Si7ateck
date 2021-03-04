package com.si7ateck.dz.ui.doctor

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.findFragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.si7ateck.dz.R
import kotlinx.coroutines.launch

class BindingAdapters {


    companion object{
        tailrec fun Context?.getActivity(): Activity? = when (this) {
            is Activity -> this

            else -> {
                val contextWrapper = this as? ContextWrapper
                contextWrapper?.baseContext?.getActivity()
            }
        }

        val View.lifecycleOwner: LifecycleOwner? get() = try {
            val fragment = findFragment<Fragment>()
            fragment.viewLifecycleOwner
        } catch (e: IllegalStateException) {
            when (val activity = context.getActivity()) {
                is ComponentActivity -> activity
                else -> null
            }
        }

        @BindingAdapter("android:getImageFromUri")
        @JvmStatic
        fun getImageFromUri(view: ShapeableImageView, imageUri: String){

            if (imageUri.equals("@drawable/images")){

                Glide.with(view.context).load(R.drawable.images)
                    .into(view)
            } else {
                Glide.with(view.context)
                    .load(imageUri)
                    .into(view)
            }

            Log.d("ImageUriAkram","imageUri is ${imageUri}" )

        }





        @BindingAdapter("android:setTextBinding")
        @JvmStatic
        fun setTextBinding(view: MaterialTextView, text : LiveData<String>){
            text.observe(view.lifecycleOwner!!, Observer { it->
                view.text=it
                Log.d("ImageUriAkram","setTextBinding called ${it}" )

            })

        }
    }
}