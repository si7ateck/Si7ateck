package com.si7ateck.dz.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.si7ateck.dz.R

class HomeViewModel : ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "Hello with us please check your name"
    }
    val text: LiveData<String> = _text



    var _items= MutableLiveData<ArrayList<String>>().apply {
        value = ArrayList<String>()
        value!!.add("res:///${R.drawable.ppng}")
        value!!.add("https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
        value!!.add("res:///${R.drawable.ppng}")
        value!!.add("https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
        value!!.add("res:///${R.drawable.ppng}")
        value!!.add("https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
    }

    val items: LiveData<ArrayList<String>>
        get() = _items






}