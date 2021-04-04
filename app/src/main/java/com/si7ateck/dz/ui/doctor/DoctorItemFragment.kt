package com.si7ateck.dz.ui.doctor

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.si7ateck.dz.R
import com.si7ateck.dz.databinding.FragmentItemListBinding
import com.si7ateck.dz.ui.doctor.adapter.Adapter
import com.si7ateck.dz.utility.filter.FiltersLayout
import com.si7ateck.dz.utility.filter.FiltersMotionLayout
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class DoctorItemFragment : Fragment(), SearchView.OnQueryTextListener, ExpandListener {

    var animationPlaybackSpeed: Double = 0.8


    private lateinit var recyclerView: RecyclerView


    private val mDoctorItemViewModel: DoctorItemViewModel by viewModels()

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    private lateinit var filtersLayout: FiltersLayout
    private lateinit var filtersMotionLayout: FiltersMotionLayout

    private val loadingDuration: Long
        get() = (resources.getInteger(R.integer.loadingAnimDuration) / animationPlaybackSpeed).toLong()


    /**
     * Used by FiltersLayout since we don't want to expose mainListAdapter (why?)
     * (Option: Combine everything into one activity if & when necessary)
     */
    var isAdapterFiltered: Boolean
        get() = adapter.isFiltered
        set(value) {
            adapter.isFiltered = value
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this

        recyclerView = _binding!!.recyclerView

        filtersLayout = _binding!!.filtersLayout
        filtersLayout.MyContext = this
        filtersMotionLayout = _binding!!.filtersMotionLayout
        filtersMotionLayout.MyContext = this




        adapter = Adapter(requireContext(), this)


        setHasOptionsMenu(true)


        setupRecyclerview()

        // Init FilterLayout
        useFiltersMotionLayout(false)
        updateRecyclerViewAnimDuration()



        mDoctorItemViewModel.getAllDoctors.observe(viewLifecycleOwner, Observer { data ->

            adapter.setData(data, mDoctorItemViewModel)
            isAdapterFiltered = true
        })

       mDoctorItemViewModel.insertdataDoctor()

/*
      mDoctorItemViewModel.deleteTest(
            Doctor(
            20,
            "1",
            "Doctor20",
            Specialty.Chirurgien_Cardiaque,
            "0540073829",
            "R.drawable.images",
            Type.PRIVATE
        ) ,
            Location(
                "20",
                2.22,
                0.5,
                City.TIPAZA,
                "tipaza",
                "1 rue",
                233
            ),
            WorkingTime(
                "20",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
*/

        return binding.root
    }


    /**
     * Callback for motionLayoutCheckbox
     * isChecked = true -> Use [FiltersMotionLayout]
     * isChecked = false -> Use [FiltersLayout]
     */
    private fun useFiltersMotionLayout(isChecked: Boolean) {
        filtersLayout.isVisible = !isChecked
        filtersMotionLayout.isVisible = isChecked
    }

    /**
     * Update RecyclerView Item Animation Durations
     */
    private fun updateRecyclerViewAnimDuration() = recyclerView.itemAnimator?.run {
        removeDuration = loadingDuration * 60 / 100
        addDuration = loadingDuration
    }

    /**
     * Called from FiltersLayout to get adapter scale down animator
     */
    fun getAdapterScaleDownAnimator(isScaledDown: Boolean): ValueAnimator =
        adapter.getScaleDownAnimator(isScaledDown)


    private fun setupRecyclerview() {
        linearLayoutManager = LinearLayoutManager(getActivity())
        recyclerView.layoutManager = linearLayoutManager


        mDoctorItemViewModel.intilizeDoctors()

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
            isAdapterFiltered = true
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }


    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        Log.d("akram", searchQuery)
        mDoctorItemViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner,
            Observer { list ->
                list?.let {
                    adapter.setData(list, mDoctorItemViewModel)
                }
            })
    }

    override fun onExpand(position: Int, size: Int) {
        if (position == 0 || position == size - 1) {
            linearLayoutManager.stackFromEnd = position != 0
        }


        if (position < linearLayoutManager.findFirstCompletelyVisibleItemPosition() || position > linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
            binding.recyclerView.smoothScrollToPosition(position)
        }

    }
}

interface ExpandListener {
    fun onExpand(position: Int, size: Int)
}


