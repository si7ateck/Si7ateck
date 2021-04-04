package com.si7ateck.dz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser


class LoginRegisterViewModel(application: Application) : AndroidViewModel(application) {
    private var authAppRepository: NetworkRepository? = null
    private var userLiveData: MutableLiveData<FirebaseUser>? = null

    init {
        authAppRepository = NetworkRepository(application)
        userLiveData = authAppRepository!!.getUserLiveData()
    }

    fun login(email: String?, password: String?) {
        authAppRepository?.login(email, password)
    }

    fun register(email: String?, password: String?) {
        authAppRepository?.register(email, password)
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser>? {
        return userLiveData
    }
}