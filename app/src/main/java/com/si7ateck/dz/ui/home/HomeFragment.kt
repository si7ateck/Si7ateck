package com.si7ateck.dz.ui.home

import android.content.ContentResolver
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.custom.sliderimage.logic.SliderImage
import com.si7ateck.dz.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )





        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home_bottom)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val res = resources.getIdentifier("ic_button_doctors","drawable","com.si7ateck.dz")

        val slider = root.findViewById<SliderImage>(R.id.slider)
        val items = ArrayList<String>()
        items.add("res:///${R.drawable.ppng}")
        items.add("https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
        slider.setItems(items)
        slider.getItems()
        slider.onPageListener(
                onPageScroll = { i: Int, fl: Float, i1: Int ->
                    Log.d("unique","onPageScroll")
                },
                onPageSelected = {position ->
                    Log.d("unique","onPageSelected")

                },
                onPageStateChange = {state ->
                    Log.d("unique","onPageStateChange")

                })
        slider.getIndicator()

        return binding.root

    }
}

