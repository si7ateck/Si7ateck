package com.si7ateck.dz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser


class LoggedinViewMode(application: Application): AndroidViewModel(application) {
    private var authAppRepository: NetworkRepository? = null
    private var userLiveData: MutableLiveData<FirebaseUser>? = null
    private var loggedOutLiveData: MutableLiveData<Boolean>? = null

    init {
        authAppRepository = NetworkRepository(application)
        userLiveData = authAppRepository!!.getUserLiveData()
        loggedOutLiveData = authAppRepository!!.getLoggedOutLiveData()
    }

    fun logOut() {
        authAppRepository?.logOut()
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser>? {
        return userLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<Boolean>? {
        return loggedOutLiveData
    }
}