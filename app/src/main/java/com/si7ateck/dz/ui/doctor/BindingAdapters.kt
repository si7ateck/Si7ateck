@file:Suppress("DEPRECATION")

package com.si7ateck.dz.ui.doctor

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.si7ateck.dz.utility.ContextUtils
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
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
            if (imageUri.equals("@drawable/images")){
                Glide.with(view.context)
                    .load(R.drawable.images)
                    .into(view)
            } else if (imageUri.equals("@drawable/ppng")){

                Glide.with(view.context)
                    .load(R.drawable.ppng)
                    .into(view)
            }else Glide.with(view.context)
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
        fun settime(view: LinearLayout, time: LiveData<String>) {
            val child1 = view.findViewById<TextView>(R.id.child1)
            val child2 = view.findViewById<TextView>(R.id.child2)
            val parent = view.findViewById<TextView>(R.id.oc)



            val currentDay = SimpleDateFormat("EEEE" ).format(Date())
            val currentTime = SimpleDateFormat("HH:mm").format(Date())



            time.observe(view.lifecycleOwner!!, Observer { it ->
                 val timeaff = it.split("\$?\$")

                if (timeaff[5].contains(" - ")){
                    val HandM = timeaff[4].split(" \\- ")
                    Log.d("timeTest", "index of ${HandM.size}")

                    if (currentDay.decapitalize().equals("thursday")) {

                        if (ContextUtils.isTimeBetweenTwoTime(HandM[0],HandM[1],currentTime)){

                            parent.text = "Open"
                            Log.d("timeTest", "${currentTime} is include in ${HandM[0]} - ${HandM[1]}}")
                        }else{
                            parent.text = "Close"
                            Log.d("timeTest", "${currentTime} is not include")}

                    } else Log.d("timeTest", "today is not thursday")

                } else {
                    parent.text = "Close"
                    Log.d("timeTest", "timeaff[5] is ${timeaff[5]} and doesn't contain ' - ' ")}


                child1.text = timeaff[0]
                child2.text = timeaff[1]
                child2.setTextColor(view.resources.getColor(R.color.primaryColor))
            })
        }
    }
}