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
            if (imageUri.equals("R.drawable.doctor1")){
                Glide.with(view.context)
                    .load(R.drawable.doctor1)
                    .into(view)
            } else if (imageUri.equals("R.drawable.doctor2")){

                Glide.with(view.context)
                    .load(R.drawable.doctor2)
                    .into(view)
            }
            else if (imageUri.equals("R.drawable.doctor3")){

                Glide.with(view.context)
                    .load(R.drawable.doctor3)
                    .into(view)
            }
            else if (imageUri.equals("R.drawable.doctor4")){

                Glide.with(view.context)
                    .load(R.drawable.doctor4)
                    .into(view)
            }
            else Glide.with(view.context)
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
/*
            val parent = view.findViewById<TextView>(R.id.oc)



            val currentDay = SimpleDateFormat("EEEE" ).format(Date())
            val currentTime = SimpleDateFormat("HH:mm").format(Date())

            var day_to_number = 0

            if (currentDay.toLowerCase().equals("monday")){
                day_to_number = 1
            } else if (currentDay.toLowerCase().equals("tuesday")){
                day_to_number = 2
            }else if (currentDay.toLowerCase().equals("wednesday")){
                day_to_number = 3
            }else if (currentDay.toLowerCase().equals("thursday")){
                day_to_number = 4
            }else if (currentDay.toLowerCase().equals("friday")){
                day_to_number = 5
            }else if (currentDay.toLowerCase().equals("saturday")){
                day_to_number = 6
            }



        time.observe(view.lifecycleOwner!!, Observer { it ->
                 val timeaff = it.split("\$?\$")

                if (timeaff[day_to_number].contains(" - ")){
                    val HandM = timeaff[day_to_number].split(" \\- ")
                    Log.d("timeTest", "index of ${HandM.size}")

                        if (ContextUtils.isTimeBetweenTwoTime(HandM[0],HandM[1],currentTime)){

                            parent.text = "Opened"
                            parent.setTextColor(view.resources.getColor(R.color.primaryColor))
                            Log.d("timeTest", "${currentTime} is include in ${HandM[0]} - ${HandM[1]}}")
                        }else{
                            parent.text = "Closed"
                            parent.setTextColor(view.resources.getColor(R.color.yellowStar))

                            Log.d("timeTest", "${currentTime} is not include")}



                } else {
                    parent.text = "Closed"
                    parent.setTextColor(view.resources.getColor(R.color.yellowStar))
                    Log.d("timeTest", "timeaff[5] is ${timeaff[5]} and doesn't contain ' - ' ")
                }


            })
*/
        }
    }
}