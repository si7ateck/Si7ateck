package com.si7ateck.dz.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.si7ateck.dz.R
import com.si7ateck.dz.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this

        _binding!!.homeViewModel = homeViewModel

        Log.d("MyFirstLogd", "before items size testing")



        homeViewModel.items.observe(viewLifecycleOwner, Observer {items ->
            items?.let {
              binding.slider.setItems(items)
          }
        })

        binding.slider.getItems()
        binding.slider.onPageListener(
            onPageScroll = { i: Int, fl: Float, i1: Int ->
                Log.d("unique", "onPageScroll")
            },
            onPageSelected = { position ->
                Log.d("unique", "onPageSelected")

            },
            onPageStateChange = { state ->
                Log.d("unique", "onPageStateChange")

            })
        binding.slider.getIndicator()

        return binding.root

    }
}

