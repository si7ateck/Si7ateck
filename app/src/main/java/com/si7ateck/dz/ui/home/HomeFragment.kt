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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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


     
            homeViewModel.text.observe(viewLifecycleOwner, Observer {
          // binding.text_home_bottom.text = it
        })


        return binding.getRoot()
    }
}