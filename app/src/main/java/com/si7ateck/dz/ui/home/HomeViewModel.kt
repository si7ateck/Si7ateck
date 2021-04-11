package com.si7ateck.dz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.si7ateck.dz.R
import java.util.ArrayList

class HomeViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "Made by @AI2B"
    }
    val text: LiveData<String> = _text

    private val _items = MutableLiveData<ArrayList<String>>().apply {
        value = ArrayList<String>()
        value!!.add("res:///${R.drawable.healthcare}")
        value!!.add("res:///${R.drawable.volunteering}")
    }

    val items: LiveData<ArrayList<String>> = _items


}