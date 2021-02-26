package com.si7ateck.dz.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.custom.sliderimage.logic.SliderImage
import com.si7ateck.dz.R
import com.si7ateck.dz.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)





        _binding!!.apply {
            lifecycleOwner = this@HomeFragment
            homeViewModel = homeViewModel



            slider.getItems()
            slider.onPageListener(
                onPageScroll = { i: Int, fl: Float, i1: Int ->
                    Log.d("unique", "onPageScroll")
                },
                onPageSelected = { position ->
                    Log.d("unique", "onPageSelected")
                },
                onPageStateChange = { state ->
                    Log.d("unique", "onPageStateChange")

                })
            slider.getIndicator()
        }
        homeViewModel.items.observe(viewLifecycleOwner, Observer { items ->
            items?.let {
                _binding!!.slider.setItems(items)
            }
        })





        return binding.root

    }
}

