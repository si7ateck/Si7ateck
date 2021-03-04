@file:Suppress("DEPRECATION")

package com.si7ateck.dz.ui.doctor

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.si7ateck.dz.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class BindingAdapters {


    companion object {
        private tailrec fun Context?.getActivity(): Activity? = when (this) {
            is Activity -> this

            else -> {
                val contextWrapper = this as? ContextWrapper
                contextWrapper?.baseContext?.getActivity()
            }
        }

        private val View.lifecycleOwner: LifecycleOwner?
            get() = try {
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
        fun getImageFromUri(view: ShapeableImageView, imageUri: String) {
            Glide.with(view.context)
                .load(imageUri)
                .into(view)

            Log.d("ImageUriAkram", "imageUri is $imageUri")

        }


        @BindingAdapter("android:setTextBinding")
        @JvmStatic
        fun setTextBinding(view: MaterialTextView, text: LiveData<String>) {
            text.observe(view.lifecycleOwner!!, Observer { it ->
                view.text = it
                Log.d("ImageUriAkram", "setTextBinding called $it")

            })

        }


        @BindingAdapter("android:settime")
        @JvmStatic
        fun settime(view: GridLayout, time: LiveData<String>) {
            val child1 = view.findViewById<TextView>(R.id.child1)
            val child2 = view.findViewById<TextView>(R.id.child2)
            val parent = view.findViewById<TextView>(R.id.oc)
            val currentDay = SimpleDateFormat("EEEE" ).format(Date())
            val currentTime = SimpleDateFormat("HH:mm").format(Date())
            val stringtoDate = SimpleDateFormat("HH:mm")
            time.observe(view.lifecycleOwner!!, Observer { it ->
                val timeaff = it.split("\$?\$")
                 val HandM = timeaff[5].split(" - ")
               // val starttime = stringtoDate.parse(HandM[0])
                val endtime = stringtoDate.parse(HandM[1])
                var calendar = Calendar.getInstance().time
                val starttime : LocalTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalTime.parse(HandM[0])
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                if (currentTime.)
                child1.text = timeaff[0]
                child2.text = timeaff[1]
                child2.setTextColor(view.resources.getColor(R.color.primaryColor))
            })
        }
    }
}