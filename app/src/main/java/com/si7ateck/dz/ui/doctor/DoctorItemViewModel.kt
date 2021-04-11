package com.si7ateck.dz.ui.doctor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.si7ateck.dz.MyDataBase
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.data.gps.Location
import com.si7ateck.dz.data.workingtime.WorkingTime
import com.si7ateck.dz.repository.DoctorRepository
import com.si7ateck.dz.repository.LocationRepository
import com.si7ateck.dz.repository.WorkingTimeRepository
import com.si7ateck.dz.ui.types.City
import com.si7ateck.dz.ui.types.Specialty
import com.si7ateck.dz.ui.types.Type
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


    private val myApplication = application

    private val doctorRepository: DoctorRepository = DoctorRepository(doctorDatabaseDao)
    private val locationRepository: LocationRepository = LocationRepository(locationDatabaseDao)
    private val workingTimeRepository: WorkingTimeRepository = WorkingTimeRepository(wtDatabaseDao)


    val getAllDoctors: LiveData<List<Doctor>> = doctorRepository.getAllDoctors


    fun deleteTest(doctor: Doctor, location: Location, workingTime: WorkingTime) {

        viewModelScope.launch(Dispatchers.IO) {
            MyDataBase.getDatabase(myApplication).runInTransaction {
                viewModelScope.launch(Dispatchers.IO) {
                    doctorRepository.delete(doctor)
                    locationRepository.delete(location)
                    workingTimeRepository.delete(workingTime)
                }
            }

        }

    }

    fun insertdataDoctor() {

        viewModelScope.launch(Dispatchers.IO) {
            MyDataBase.getDatabase(myApplication).runInTransaction {
                viewModelScope.launch(Dispatchers.IO) {
                    initializeLocationOfDoctors()
                    initializeWorkingTime()
                    intilizeDoctors()
                }
            }

        }

    }


    fun getAddress(id: String): LiveData<String> {
        return doctorRepository.getAddress(id)
    }


    fun getWT(id: String): LiveData<String> {
        return doctorRepository.getWT(id)
    }

    private val _DiplayDocotors = MutableLiveData<ArrayList<Doctor>>().apply {
        value = ArrayList<Doctor>()
        value!!.add(
            Doctor(
                1,
                "1",
                "Akram Bensalem",
                Specialty.Urologue,
                "0540073829",
                "R.drawable.doctor1",
                Type.PRIVATE
            )
        )
        value!!.add(
            Doctor(
                2,
                "2",
                "Belmiloud Ilies",
                Specialty.Radiologue,
                "0540073829",
                "R.drawable.doctor2",
                Type.PUBLIC
            )
        )
        value!!.add(
            Doctor(
                3,
                "3",
                "Arbaoui Slimane",
                Specialty.Allergologue,
                "0540073829",
                "R.drawable.doctor3",
                Type.PUBLIC
            )
        )
        value!!.add(
            Doctor(
                4,
                "4",
                "Hamadou Bourhan",
                Specialty.Orthopediste,
                "0540073829",
                "R.drawable.doctor4",
                Type.PRIVATE
            )
        )
    }

    val DisplayDocotors: LiveData<ArrayList<Doctor>> = _DiplayDocotors

    private val _locationitems = MutableLiveData<ArrayList<Location>>().apply {
        value = ArrayList<Location>()
        value!!.add(
            Location(
                "1",
                2.22,
                0.5,
                City.TIPAZA,
                "Tipaza",
                "Ain Bensultan",
                233
            )
        )
        value!!.add(
            Location(
                "2",
                2.223,
                0.05,
                City.MEDEA,
                "Ain D'hab",
                "Ain D'hab",
                2334
            )
        )
        value!!.add(
            Location(
                "3",
                2.225,
                0.45,
                City.TIZI_OUZOU,
                "Ain D'hab",
                "2 Rue Horiya",
                2335
            )
        )
        value!!.add(
            Location(
                "4",
                2.227,
                0.35,
                City.ORAN,
                "Ain Abtal",
                "5 Rue El Nasr",
                2337
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
    }
    val wtitems: LiveData<ArrayList<WorkingTime>> = _wtitems


    fun insertDoctor(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            doctorRepository.insert(doctor)
        }
    }

    fun insertLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            locationRepository.insert(location)
        }
    }

    fun insertWorkingTime(workingTime: WorkingTime) {
        viewModelScope.launch(Dispatchers.IO) {
            workingTimeRepository.insert(workingTime)
        }
    }

    fun intilizeDoctors() {

        _DiplayDocotors.value?.forEach { item ->
            insertDoctor(item)
        }
    }

    fun initializeLocationOfDoctors() {

        locationitems.value?.forEach { item ->
            insertLocation(item)
        }
    }

    fun initializeWorkingTime() {

        wtitems.value?.forEach { item ->
            insertWorkingTime(item)
        }
    }

    fun updateData(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            doctorRepository.update(doctor)
        }
    }


    fun deleteItem() {
        viewModelScope.launch(Dispatchers.IO) {
            doctorRepository.clear()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Doctor>> {
        return doctorRepository.searchDatabase(searchQuery)
    }


}



