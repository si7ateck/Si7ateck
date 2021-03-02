package com.si7ateck.dz.ui.doctor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.si7ateck.dz.databinding.FragmentItemListBinding
import com.si7ateck.dz.ui.doctor.adapter.Adapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class DoctorItemFragment: Fragment(),SearchView.OnQueryTextListener {

    private val mDoctorItemViewModel: DoctorItemViewModel by viewModels()

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private lateinit var  adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        adapter = Adapter()

        setupRecyclerview()
        mDoctorItemViewModel.getAllDoctors.observe(viewLifecycleOwner, Observer {data ->

               adapter.setData(data,mDoctorItemViewModel)
           })


        return binding.root
    }
    private fun setupRecyclerview() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }
    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"

        mDoctorItemViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner,
            Observer { list -> list?.let {
                adapter.setData(list, mDoctorItemViewModel)
            }
        })
    }
}