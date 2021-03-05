package com.si7ateck.dz.ui.doctor

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.si7ateck.dz.R
import com.si7ateck.dz.databinding.FragmentItemListBinding
import com.si7ateck.dz.ui.doctor.adapter.Adapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class DoctorItemFragment: Fragment(), SearchView.OnQueryTextListener, ExpandListener {

    private val mDoctorItemViewModel: DoctorItemViewModel by viewModels()

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private lateinit var  adapter: Adapter
    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        adapter = Adapter(this)


        setHasOptionsMenu(true)

        setupRecyclerview()
        mDoctorItemViewModel.getAllDoctors.observe(viewLifecycleOwner, Observer {data ->

               adapter.setData(data,mDoctorItemViewModel)
           })

        mDoctorItemViewModel.intilizeDatad()

        return binding.root
    }
    private fun setupRecyclerview() {
        val recyclerView = binding.recyclerView

        linearLayoutManager = LinearLayoutManager(getActivity())
        recyclerView.layoutManager = linearLayoutManager


        mDoctorItemViewModel.intilizeDatad()

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
        Log.d("akram",searchQuery)
        mDoctorItemViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner,
            Observer { list -> list?.let {
                adapter.setData(list, mDoctorItemViewModel)
            }
        })
    }

    override fun onExpand(position: Int, size : Int) {
        // linearLayoutManager.stackFromEnd = linearLayoutManager.stackFromEnd != true

        val firstVisiblePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
        val lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()

//        linearLayoutManager.stackFromEnd =
//            !(position == firstVisiblePosition || position == firstVisiblePosition +1)


        if (position == firstVisiblePosition || position == lastVisiblePosition - 1) {
            linearLayoutManager.stackFromEnd = position != firstVisiblePosition

            /*   if ( position == 0 || position == size - 1  ) {

            linearLayoutManager.stackFromEnd = position != firstVisiblePosition

        } else if (position == firstVisiblePosition || position == firstVisiblePosition + 1){

            binding.recyclerView.smoothScrollToPosition(position-1)
        } else if (position == lastVisiblePosition ){
            binding.recyclerView.smoothScrollToPosition(position+1)

        }*/

            Log.d("RecyclerBehavior", "first pos is ${firstVisiblePosition}")
            Log.d("RecyclerBehavior", "last pos is ${lastVisiblePosition}")


        }

    }
}
interface ExpandListener {
    fun onExpand(position: Int, size : Int)
}


