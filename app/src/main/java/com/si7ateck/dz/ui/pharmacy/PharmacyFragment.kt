package com.si7ateck.dz.ui.pharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.si7ateck.dz.databinding.FragmentItemListBinding
import com.si7ateck.dz.ui.pharmacy.adapter.AdapterPharmacy
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class PharmacyFragment : Fragment(), SearchView.OnQueryTextListener {

    private val mpharmacyViewModel: PharmacyViewModel by viewModels()

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private val adapter: AdapterPharmacy by lazy { AdapterPharmacy() }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this


        setupRecyclerview()

        mpharmacyViewModel.getAllPharmacy.observe(viewLifecycleOwner, Observer {data ->
            adapter.setDatap(data)
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
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }
}