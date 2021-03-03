package com.si7ateck.dz.ui.doctor

import android.app.Application
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.si7ateck.dz.MyDataBase
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.data.gps.Location
import com.si7ateck.dz.data.types.City
import com.si7ateck.dz.data.types.Specialty
import com.si7ateck.dz.data.types.Type
import com.si7ateck.dz.data.workingtime.WorkingTime
import com.si7ateck.dz.repository.DoctorRepository
import com.si7ateck.dz.repository.LocationRepository
import com.si7ateck.dz.repository.WorkingTimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DoctorItemViewModel(application: Application) : AndroidViewModel(application) {
    private val doctorDatabaseDao = MyDataBase.getDatabase(
        application
    ).doctorDatabaseDao()
    private val wtDatabaseDao = MyDataBase.getDatabase(
        application
    ).wtDatabaseDao()
    private val locationDatabaseDao = MyDataBase.getDatabase(
        application
    ).locationDatabaseDao()
    private val repositoryd: DoctorRepository = DoctorRepository(doctorDatabaseDao)
    private val repositoryl: LocationRepository = LocationRepository(locationDatabaseDao)
    private val repositorywt: WorkingTimeRepository = WorkingTimeRepository(wtDatabaseDao)
    val getAllDoctors: LiveData<List<Doctor>> = repositoryd.getAllDoctors


      fun getAddress(id:String):LiveData<String> {
        return repositoryd.getAddress(id)
    }
    fun getWT(id: String):LiveData<String>{
        return repositoryd.getWT(id)
    }

    private val _DiplayDocotors = MutableLiveData<ArrayList<Doctor>>().apply {
        value = ArrayList<Doctor>()
        value!!.add(
            Doctor(
                6,
                "1",
                "Doctor1",
                Specialty.Chirurgien_Cardiaque,
                "0540073829",
                "R.drawable.images",
                Type.PRIVATE
            )
        )
        value!!.add(
            Doctor(
                7,
                "2",
                "Doctor2",
                Specialty.Chirurgie_pediatrique,
                "0540073829",
                "R.drawable.ppng",
                Type.PRIVATE
            )
        )
        value!!.add(
            Doctor(
                8,
                "3",
                "Doctor3",
                Specialty.Allergologue,
                "0540073829",
                "R.drawable.ppng",
                Type.PRIVATE
            )
        )
        value!!.add(
            Doctor(
                9,
                "4",
                "Doctor4",
                Specialty.Allergologue,
                "0540073829",
                "R.drawable.images",
                Type.PRIVATE
            )
        )
        value!!.add(
            Doctor(
                10,
                "5",
                "Doctor5",
                Specialty.Allergologue,
                "0540073829",
                "R.drawable.images",
                Type.PRIVATE
            )
        )
    }

    val DiplayDocotors: LiveData<ArrayList<Doctor>> = _DiplayDocotors

    private val _locationitems = MutableLiveData<ArrayList<Location>>().apply {
        value = ArrayList<Location>()
        value!!.add(
            Location(
                "1",
                2.22,
                0.5,
                City.TIPAZA,
                "tipaza",
                "1 rue",
                233
            )
        )
        value!!.add(
            Location(
                "2",
                2.223,
                0.05,
                City.MEDEA,
                "dar el berd",
                "2 rue belquace",
                2334
            )
        )
        value!!.add(
            Location(
                "3",
                2.225,
                0.45,
                City.TIZI_OUZOU,
                "dar el berd",
                "2 rue belquace",
                2335
            )
        )
        value!!.add(
            Location(
                "4",
                2.227,
                0.35,
                City.ORAN,
                "dar el berd",
                "2 rue belquace",
                2337
            )
        )
        value!!.add(
            Location(
                "5",
                2.228,
                0.75,
                City.BLIDA,
                "dar el berd",
                "2 rue belquace",
                2339
            )
        )
    }
    val locationitems: LiveData<ArrayList<Location>> = _locationitems

    private val _wtitems = MutableLiveData<ArrayList<WorkingTime>>().apply {
        value = ArrayList<WorkingTime>()
        value!!.add(
            WorkingTime(
                "1",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "2",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "3",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "4",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
        value!!.add(
            WorkingTime(
                "5",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "08:00 - 17:00",
                "not workin",
                "not workin"
            )
        )
    }
    val wtitems: LiveData<ArrayList<WorkingTime>> = _wtitems


    fun insertDatad(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryd.insert(doctor)
        }
    }

    fun insertDatal(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryl.insert(location)
        }
    }

    fun insertDatawt(workingTime: WorkingTime) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorywt.insert(workingTime)
        }
    }

    fun intilizeDatad() {

        _DiplayDocotors.value?.forEach { item ->
            insertDatad(item)
        }
    }

    fun intilizeDatal() {

        locationitems.value?.forEach { item ->
            insertDatal(item)
        }
    }

    fun intilizeDatawt() {

        wtitems.value?.forEach { item ->
            insertDatawt(item)
        }
    }

    fun updateData(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryd.update(doctor)
        }
    }


    fun deleteItem() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryd.clear()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Doctor>> {
        return repositoryd.searchDatabase(searchQuery)
    }



}



