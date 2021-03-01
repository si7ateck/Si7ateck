package com.si7ateck.dz.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.si7ateck.dz.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeViewModelFactory: HomeViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModelFactory=HomeViewModelFactory()

        homeViewModel =
            ViewModelProvider(this,homeViewModelFactory).get(HomeViewModel::class.java)

        _binding!!.lifecycleOwner = this
        _binding!!.homeViewModel = homeViewModel


        _binding!!.apply {

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

